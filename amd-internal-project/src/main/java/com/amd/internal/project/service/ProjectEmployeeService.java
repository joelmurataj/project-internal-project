package com.amd.internal.project.service;

import com.amd.internal.project.dto.ProjectEmployeeDto;

public interface ProjectEmployeeService {

	public void save(ProjectEmployeeDto projectEmployee);
	public void findById(int projetId, int userId);
	
	public void remove(int projectId, int userId);
}
