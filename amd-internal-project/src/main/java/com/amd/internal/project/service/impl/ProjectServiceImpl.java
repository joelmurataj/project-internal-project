package com.amd.internal.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	public List<ProjectDto> findAll() {
		return ProjectConverter
				.toProjectListDto(projectdao.findByFlag(true, new Sort(Sort.Direction.DESC, "startDate")));
	}

	@Override
	@Transactional
	public void save(Project project) {
		ProjectDto projectDto = projectdao.findByName(project.getName());
		if (projectDto == null) {
			projectdao.save(project);
		}
	}

	@Override
	@Transactional
	public ProjectDto deleteProject(int projectId) {
		Project project = projectdao.findById(projectId).get();
		project.setFlag(false);

		return ProjectConverter.toProjectDto(project);
	}

	@Override
	@Transactional
	public ProjectDto updateProject(Project project) {
		projectdao.saveAndFlush(project);
		return ProjectConverter.toProjectDto(project);
	}

	@Override
	public List<ProjectDto> searchByName(String name) {
		List<Project> project =projectdao.searchByName(name);
		return ProjectConverter.toProjectListDto(project);
	}

}
