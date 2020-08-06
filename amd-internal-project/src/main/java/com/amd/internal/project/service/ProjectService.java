package com.amd.internal.project.service;

import java.util.List;

import com.amd.internal.project.dto.ProjectDto;
import com.amd.internal.project.dto.UserDto;

public interface ProjectService {

	public ProjectDto findById(int projectId);
	public List<ProjectDto> findAll();
	public ProjectDto save(ProjectDto project);
	public ProjectDto deleteProject(int projectId);
	public ProjectDto updateProject(ProjectDto project, int projectId);
	public List<ProjectDto> searchByName(String name);
	
	public List<UserDto> getEmployeesOfProject(int id);
}
