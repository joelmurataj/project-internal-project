package com.amd.internal.project.service.impl;

import java.util.ArrayList;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amd.internal.project.converter.ProjectEmployeeConverter;
import com.amd.internal.project.converter.UserConverter;
import com.amd.internal.project.dao.ProjectDao;
import com.amd.internal.project.dao.ProjectEmployeeDao;
import com.amd.internal.project.dao.UserDao;
import com.amd.internal.project.dto.ProjectEmployeeDto;
import com.amd.internal.project.dto.UserDto;
import com.amd.internal.project.entity.Project;
import com.amd.internal.project.entity.ProjectEmployee;
import com.amd.internal.project.entity.User;
import com.amd.internal.project.service.ProjectEmployeeService;

@Service
public class ProjectEmployeeServiceImpl implements ProjectEmployeeService {

	@Autowired
	ProjectEmployeeDao projectEmployeeDao;
	
	@Autowired
	UserDao userDao;

	@Autowired
	ProjectDao projectDao;
	
	@Override
	@Transactional
	public void save(ProjectEmployeeDto projectEmployeeDto) {
		if (projectEmployeeDto != null) {
			Project project = projectDao.findById(projectEmployeeDto.getProjectId()).get();
			int vacancy =project.getVacancy();
			if(vacancy>0) {
			projectEmployeeDao.save(ProjectEmployeeConverter.toEmploeeProject(projectEmployeeDto));
			project.setVacancy(vacancy-1);
			}
		}
	}

	@Override
	public void findById(int projetId, int userId) {
		projectEmployeeDao.getProjectEmployee(projetId, userId);
		
	}

	@Override
	public void remove(int projectId, int userId) {
		Project project = projectDao.findById(projectId).get();
		int vacancy = project.getVacancy();
		project.setVacancy(vacancy+1);
		projectEmployeeDao.remove(projectId, userId);
	}

	@Override
	public void update(ProjectEmployeeDto projectEmployee) {
		projectEmployee.setActivated(true);
		projectEmployeeDao.update(ProjectEmployeeConverter.toEmploeeProject(projectEmployee));
	}

	@Override
	public ArrayList<UserDto> filterByNameAndDates(String firstNameOfEmployee, Date startDate,
			Date finishedDate) {
		ArrayList<UserDto> listOfUserDto = new ArrayList<UserDto>();
		ArrayList<ProjectEmployee> projectEmployees =projectEmployeeDao.filterByNameAndDates(firstNameOfEmployee, startDate, finishedDate);
		for(ProjectEmployee projectemploee : projectEmployees) {
			UserDto userDto = UserConverter.toUserDto(userDao.findById(projectemploee.getUser().getId()).get());
			userDto.setStartDateInProject(projectemploee.getStartDateEmployee());
			userDto.setFinishedDateInProject(projectemploee.getFinishedDateEmployee());
			listOfUserDto.add(userDto);
		}
		return listOfUserDto;
	}

}
