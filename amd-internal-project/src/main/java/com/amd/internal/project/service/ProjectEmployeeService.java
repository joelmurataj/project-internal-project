package com.amd.internal.project.service;

import java.util.ArrayList;

import com.amd.internal.project.dto.ProjectEmployeeDto;
import com.amd.internal.project.dto.UserDto;

public interface ProjectEmployeeService {

	public ProjectEmployeeDto save(ProjectEmployeeDto projectEmployee);
	
	public void remove(int projectId, int userId);
	public void update(ProjectEmployeeDto projectEmployee);
	public ArrayList<UserDto> filterByNameAndDates(UserDto userDto,int id);
}
