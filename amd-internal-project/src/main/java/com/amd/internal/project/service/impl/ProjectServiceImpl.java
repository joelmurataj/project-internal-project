package com.amd.internal.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amd.internal.project.converter.ProjectConverter;
import com.amd.internal.project.dao.ProjectDao;
import com.amd.internal.project.dto.ProjectDto;
import com.amd.internal.project.entity.Project;
import com.amd.internal.project.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectDao projectdao;

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
	public List<Project> findAll() {
		return projectdao.findByFlag(true);
	}

	@Override
	@Transactional
	public void save(Project project) {
		projectdao.save(project);

	}

	@Override
	@Transactional
	public Project deleteProject(int projectId) {
		Project project = projectdao.findById(projectId).get();
		project.setFlag(false);
		projectdao.saveAndFlush(project);
		return project;
	}

	@Override
	@Transactional
	public Project updateProject(Project project) {
		projectdao.saveAndFlush(project);
		return project;
	}

}
