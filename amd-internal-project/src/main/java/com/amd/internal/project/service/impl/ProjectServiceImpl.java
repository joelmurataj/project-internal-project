package com.amd.internal.project.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amd.internal.project.converter.ProjectConverter;
import com.amd.internal.project.converter.UserConverter;
import com.amd.internal.project.dao.ProjectDao;
import com.amd.internal.project.dao.ProjectEmployeeDao;
import com.amd.internal.project.dto.ProjectDto;
import com.amd.internal.project.dto.UserDto;
import com.amd.internal.project.entity.Departament;
import com.amd.internal.project.entity.Project;
import com.amd.internal.project.entity.ProjectEmployee;
import com.amd.internal.project.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectDao projectdao;

	@Autowired
	ProjectEmployeeDao projectEmployeedao;

	@Override
	@Transactional
	public ProjectDto findById(int projectId) {
		Project project = projectdao.findById(projectId).get();
		if (project.isFlag()) {
			return ProjectConverter.toProjectDto(project);
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public List<ProjectDto> findAll() {
		UserDto userDto=(UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Departament departament= new Departament();
		departament.setId(userDto.getDepartamentId());
		return ProjectConverter
				.toProjectListDto(projectdao.findByFlagAndDepartament(true, departament,new Sort(Sort.Direction.DESC, "startDate")));
	}

	@Override
	@Transactional
	public void save(ProjectDto projectDto) {
		Project project = projectdao.findByName(projectDto.getName());
		if (project == null) {
			projectDto.setFlag(true);
			UserDto userLoggedIn = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			projectDto.setDepartamentId(userLoggedIn.getDepartamentId());
			projectDto.setVacancy(projectDto.getMaxOfEmployee());
			projectdao.save(ProjectConverter.toProject(projectDto));
		}
	}

	@Override
	@Transactional
	public ProjectDto deleteProject(int projectId) {
		Project project = projectdao.findById(projectId).get();
		if(getEmployeesOfProject(projectId).isEmpty()){
			project.setFlag(false);
			return ProjectConverter.toProjectDto(project);
		}
		else {
			return null;
		}
		
	}

	@Override
	@Transactional
	public ProjectDto updateProject(ProjectDto projectDto, int projectId) {
		if (projectEmployeedao.projectConflictDateWithEmployee(projectId, projectDto.getStartDate(), projectDto.getFinishedDate())
				.isEmpty()) {
			Project project = projectdao.findById(projectId).get();
			project.setProjectDetail(projectDto.getProjectDetail());
			project.setStartDate(projectDto.getStartDate());
			project.setFinishedDate(projectDto.getFinishedDate());
			int vacancyAddded = projectDto.getMaxOfEmployee() -project.getMaxOfEmployee();
			project.setVacancy(project.getVacancy()+vacancyAddded);
			project.setMaxOfEmployee(projectDto.getMaxOfEmployee());
			return ProjectConverter.toProjectDto(project);
		}
		return null;
	}

	@Override
	@Transactional
	public List<ProjectDto> searchByName(String name) {
		List<Project> project = projectdao.searchByName(name);
		return ProjectConverter.toProjectListDto(project);
	}

	@Override
	@Transactional
	public Set<UserDto> getEmployeesOfProject(int id) {
		Set<UserDto> listOfUserDto1 = new HashSet<UserDto>();
		ProjectDto project = findById(id);
		Set<UserDto> listOfUserDto = UserConverter.toUserSetDto(project.getEmployees());
		for (UserDto userDto : listOfUserDto) {
			ArrayList<ProjectEmployee> listOfProjectEmployee = projectEmployeedao.getProjectEmployee(id,
					userDto.getId());
			if (!listOfProjectEmployee.isEmpty()) {
				for (ProjectEmployee projectemployee : listOfProjectEmployee) {
					if (projectemployee.isActivated()) {
						userDto.setStartDateInProject(projectemployee.getStartDateEmployee());
						userDto.setFinishedDateInProject(projectemployee.getFinishedDateEmployee());
						listOfUserDto1.add(userDto);
					}
				}
			}
		}
		return listOfUserDto1;
	}

}
