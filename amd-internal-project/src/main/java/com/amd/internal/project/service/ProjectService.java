package com.amd.internal.project.service;

import java.util.List;
import java.util.Set;

import com.amd.internal.project.dto.ProjectDto;
import com.amd.internal.project.dto.UserDto;
import com.amd.internal.project.entity.Project;
import com.amd.internal.project.entity.User;

public interface ProjectService {

	public ProjectDto findById(int projectId);
	public List<ProjectDto> findAll();
	public void save(Project project);
	public ProjectDto deleteProject(int projectId);
	public ProjectDto updateProject(Project project, int projectId);
	public List<ProjectDto> searchByName(String name);
	
	public Set<UserDto> getEmployeesOfProject(int id);
}
