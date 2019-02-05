package com.amd.internal.project.converter;

import com.amd.internal.project.dto.ProjectDto;
import com.amd.internal.project.entity.Project;

public class ProjectConverter {

	public static ProjectDto toProjectDto(Project project) {
		if (project != null) {
			ProjectDto projectDto = new ProjectDto();
			projectDto.setId(project.getId());
			projectDto.setName(project.getName());
			projectDto.setPricePerMonth(project.getPricePerMonth());
			projectDto.setProjectDetail(project.getProjectDetail());
			projectDto.setStartDate(project.getStartDate());
			projectDto.setFlag(project.isFlag());
			return projectDto;
		} else {
			return null;
		}

	}
}
