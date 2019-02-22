package com.amd.internal.project.service.impl;

import java.util.ArrayList;

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
			int vacancy = project.getVacancy();
			if (vacancy > 0) {
				//IMPORTANT those comments are for future add employee when you place even the allocation too
				
				// List<ProjectEmployee> projectEmployees =
				// projectEmployeeDao.getProjectOfEmployee(
				// projectEmployeeDto.getUserId(), projectEmployeeDto.getStartDateEmployee(),
				// projectEmployeeDto.getFinishedDateEmployee());
				/// if (projectEmployees != null) {
				// int allocation = 0;
				// for (int i = 0; i < projectEmployees.size(); i++) {
				// allocation += projectEmployees.get(i).getAllocation();
				// }
				// allocation += projectEmployeeDto.getAllocation();
				// if (allocation <= 100) {
				projectEmployeeDao.save(ProjectEmployeeConverter.toEmploeeProject(projectEmployeeDto));
				project.setVacancy(vacancy - 1);
				// }
				// }

			}
		}
	}

	@Override
	public void remove(int projectId, int userId) {
		Project project = projectDao.findById(projectId).get();
		int vacancy = project.getVacancy();
		project.setVacancy(vacancy + 1);
		projectEmployeeDao.remove(projectId, userId);
	}

	@Override
	public void update(ProjectEmployeeDto projectEmployee) {
		projectEmployee.setActivated(true);
		projectEmployeeDao.update(ProjectEmployeeConverter.toEmploeeProject(projectEmployee));
	}

	@Override
	public ArrayList<UserDto> filterByNameAndDates(UserDto userDto, int id) {
		if (userDto.getFirstName() == null) {
			userDto.setFirstName("");
		}
		ArrayList<UserDto> listOfUserDto = new ArrayList<UserDto>();
		ArrayList<ProjectEmployee> projectEmployees = projectEmployeeDao.filterByNameAndDates(userDto.getFirstName(),
				userDto.getStartDateInProject(), userDto.getFinishedDateInProject(), id);
		for (ProjectEmployee projectemploee : projectEmployees) {
			UserDto userDto1 = UserConverter.toUserDto(userDao.findById(projectemploee.getUser().getId()).get());
			userDto1.setStartDateInProject(projectemploee.getStartDateEmployee());
			userDto1.setFinishedDateInProject(projectemploee.getFinishedDateEmployee());
			userDto1.setAllocation(projectemploee.getAllocation());
			listOfUserDto.add(userDto1);
		}
		return listOfUserDto;
	}

}
