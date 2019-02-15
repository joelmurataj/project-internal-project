package com.amd.internal.project.service;

import java.util.ArrayList;
import java.util.Date;

import com.amd.internal.project.dto.ProjectEmployeeDto;
import com.amd.internal.project.dto.UserDto;

public interface ProjectEmployeeService {

	public void save(ProjectEmployeeDto projectEmployee);
	public void findById(int projetId, int userId);
	
	public void remove(int projectId, int userId);
	public void update(ProjectEmployeeDto projectEmployee);
	public ArrayList<UserDto> filterByNameAndDates(String firstNameOfEmployee, Date startDate, Date finishedDate);
}
