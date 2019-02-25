package com.amd.internal.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
import com.amd.internal.project.entity.Status;
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
		try {
			Project project = projectdao.findById(projectId).get();
			if (project.isFlag()) {
				return ProjectConverter.toProjectDto(project);
			} else {
				return null;
			}
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	@Transactional
	public List<ProjectDto> findAll() {
		UserDto userDto = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Departament departament = new Departament();
		departament.setId(userDto.getDepartamentId());
		return ProjectConverter.toProjectListDto(
				projectdao.findByFlagAndDepartament(true, departament, new Sort(Sort.Direction.DESC, "startDate")));
	}

	@Override
	@Transactional
	public ProjectDto save(ProjectDto projectDto) {
		Project project = projectdao.findByName(projectDto.getName());
		if (project == null) {
			projectDto.setFlag(true);
			UserDto userLoggedIn = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			projectDto.setDepartamentId(userLoggedIn.getDepartamentId());
			projectDto.setVacancy(projectDto.getMaxOfEmployee());
			projectdao.save(ProjectConverter.toProject(projectDto));
			return projectDto;
		}
		return null;
	}

	@Override
	@Transactional
	public ProjectDto deleteProject(int projectId) {
		Project project = projectdao.findById(projectId).get();
		if (getEmployeesOfProject(projectId).isEmpty()) {
			project.setFlag(false);
			return ProjectConverter.toProjectDto(project);
		} else {
			return null;
		}

	}

	@Override
	@Transactional
	public ProjectDto updateProject(ProjectDto projectDto, int projectId) {
		if (projectEmployeedao
				.projectConflictDateWithEmployee(projectId, projectDto.getStartDate(), projectDto.getFinishedDate())
				.isEmpty()) {
			Project project = projectdao.findById(projectId).get();
			int vacancy = projectDto.getMaxOfEmployee() - project.getMaxOfEmployee();
			if (project.getVacancy() + vacancy >= 0) {
				project.setProjectDetail(projectDto.getProjectDetail());
				project.setStartDate(projectDto.getStartDate());
				project.setFinishedDate(projectDto.getFinishedDate());
				Status status = new Status();
				status.setId(projectDto.getStatusId());
				project.setStatus(status);
				int vacancyAddded = projectDto.getMaxOfEmployee() - project.getMaxOfEmployee();
				project.setVacancy(project.getVacancy() + vacancyAddded);
				project.setMaxOfEmployee(projectDto.getMaxOfEmployee());
				return ProjectConverter.toProjectDto(project);
			}
		}
		return null;
	}

	@Override
	@Transactional
	public List<ProjectDto> searchByName(String name) {
		UserDto userLogged = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Departament departament = new Departament();
		departament.setId(userLogged.getDepartamentId());
		List<Project> project = projectdao.searchByNameAndDepartament(name, departament);
		return ProjectConverter.toProjectListDto(project);
	}

	@Override
	@Transactional
	public List<UserDto> getEmployeesOfProject(int id) {
		List<UserDto> listOfUserDto1 = new ArrayList<UserDto>();
		ProjectDto project = findById(id);
		List<UserDto> listOfUserDto = UserConverter.toUserListDto(project.getEmployees());
		for (UserDto userDto : listOfUserDto) {
			ProjectEmployee listOfProjectEmployee = projectEmployeedao.getActivatedProjectEmployee(id, userDto.getId());
			if (listOfProjectEmployee != null) {
				userDto.setStartDateInProject(listOfProjectEmployee.getStartDateEmployee());
				userDto.setFinishedDateInProject(listOfProjectEmployee.getFinishedDateEmployee());
				userDto.setAllocation(listOfProjectEmployee.getAllocation());
				listOfUserDto1.add(userDto);
			}
		}
		return listOfUserDto1;
	}

}
