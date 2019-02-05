package com.amd.internal.project.service;

import java.util.List;

import com.amd.internal.project.dto.ProjectDto;
import com.amd.internal.project.entity.Project;

public interface ProjectService {

	public ProjectDto findById(int projectId);
	public List<Project> findAll();
	public void save(Project project);
	public Project deleteProject(int projectId);
	public Project updateProject(Project project);
}
