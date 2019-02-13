package com.amd.internal.project.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amd.internal.project.converter.ProjectConverter;
import com.amd.internal.project.converter.UserConverter;
import com.amd.internal.project.dao.ProjectDao;
import com.amd.internal.project.dao.ProjectEmployeeDao;
import com.amd.internal.project.dto.ProjectDto;
import com.amd.internal.project.dto.ProjectEmployeeDto;
import com.amd.internal.project.dto.UserDto;
import com.amd.internal.project.entity.Project;
import com.amd.internal.project.entity.ProjectEmployee;
import com.amd.internal.project.entity.User;
import com.amd.internal.project.service.ProjectService;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
		return ProjectConverter
				.toProjectListDto(projectdao.findByFlag(true, new Sort(Sort.Direction.DESC, "startDate")));
	}

	@Override
	@Transactional
	public void save(Project project) {
		ProjectDto projectDto = projectdao.findByName(project.getName());
		if (projectDto == null) {
			projectdao.save(project);
		}
	}

	@Override
	@Transactional
	public ProjectDto deleteProject(int projectId) {
		Project project = projectdao.findById(projectId).get();
		if(getEmployeesOfProject(projectId).isEmpty()){
			project.setFlag(false);
		}
		
		return ProjectConverter.toProjectDto(project);
	}

	@Override
	@Transactional
	public ProjectDto updateProject(Project project, int projectId) {
		if (projectEmployeedao.projectConflictDateWithEmployee(project.getStartDate(), project.getFinishedDate())
				.isEmpty()) {
			System.out.println("u updetua");
			Project project1 = projectdao.findById(projectId).get();
			project1.setProjectDetail(project.getProjectDetail());
			project1.setFinishedDate(project.getFinishedDate());
			project1.setStartDate(project.getStartDate());
		}else {
			System.out.println("ka konflikte");
		}
		return ProjectConverter.toProjectDto(project);
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
