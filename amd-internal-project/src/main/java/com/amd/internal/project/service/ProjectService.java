package com.amd.internal.project.service;

import java.util.List;

import com.amd.internal.project.dto.ProjectDto;
import com.amd.internal.project.entity.Project;

public interface ProjectService {

	public ProjectDto findById(int projectId);
	public List<ProjectDto> findAll();
	public void save(Project project);
	public ProjectDto deleteProject(int projectId);
	public ProjectDto updateProject(Project project);
	public List<ProjectDto> searchByName(String name);
}
