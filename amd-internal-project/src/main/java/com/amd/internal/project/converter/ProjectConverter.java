package com.amd.internal.project.converter;

import java.util.ArrayList;
import java.util.List;

import com.amd.internal.project.dto.ProjectDto;
import com.amd.internal.project.entity.Departament;
import com.amd.internal.project.entity.Project;
import com.amd.internal.project.entity.Status;

public class ProjectConverter {

	public static ProjectDto toProjectDto(Project project) {
		if (project != null) {
			ProjectDto projectDto = new ProjectDto();
			projectDto.setId(project.getId());
			projectDto.setName(project.getName());
			projectDto.setPricePerMonth(project.getPricePerMonth());
			projectDto.setProjectDetail(project.getProjectDetail());
			projectDto.setStartDate(project.getStartDate());
			projectDto.setFinishedDate(project.getFinishedDate());
			projectDto.setFlag(project.isFlag());
			projectDto.setDepartamentId(project.getDepartament().getId());
			projectDto.setDepartamentName(project.getDepartament().getName());
			projectDto.setVacancy(project.getVacancy());
			projectDto.setMaxOfEmployee(project.getMaxOfEmployee());
			projectDto.setStatusId(project.getStatus().getId());
			projectDto.setStatusName(project.getStatus().getName());
			if (!project.getEmployees().isEmpty()) {
				projectDto.setEmployees(project.getEmployees());
			}
			return projectDto;
		} else {
			return null;
		}

	}
	
//	public static Project toEditProjectdto(ProjectDto projectDto) {
//		if (projectDto != null) {
//			Project project = new Project();
//			project.setId(projectDto.getId());
//			project.setName(projectDto.getName());
//			project.setPricePerMonth(projectDto.getPricePerMonth());
//			project.setProjectDetail(projectDto.getProjectDetail());
//			project.setStartDate(projectDto.getStartDate());
//			project.setFinishedDate(projectDto.getFinishedDate());
//			project.setFlag(projectDto.isFlag());
//			Departament departament = new Departament();
//			departament.setId(projectDto.getDepartamentId());
//			project.setDepartament(departament);
//			project.setVacancy(projectDto.getVacancy());
//			project.setMaxOfEmployee(projectDto.getMaxOfEmployee());
//			if (!projectDto.getEmployees().isEmpty()) {
//				project.setEmployees(projectDto.getEmployees());
//			}
//			return project;
//		} else {
//			return null;
//		}
//	}
	
	public static Project toProject(ProjectDto projectDto) {
		if (projectDto != null) {
			Project project = new Project();
			project.setId(projectDto.getId());
			project.setName(projectDto.getName());
			project.setPricePerMonth(projectDto.getPricePerMonth());
			project.setProjectDetail(projectDto.getProjectDetail());
			project.setStartDate(projectDto.getStartDate());
			project.setFinishedDate(projectDto.getFinishedDate());
			project.setFlag(projectDto.isFlag());
			Departament departament = new Departament();
			departament.setId(projectDto.getDepartamentId());
			project.setDepartament(departament);
			project.setVacancy(projectDto.getVacancy());
			project.setMaxOfEmployee(projectDto.getMaxOfEmployee());
			Status status = new Status();
			status.setId(projectDto.getStatusId());
			project.setStatus(status);
			if (projectDto.getEmployees() !=null) {
				project.setEmployees(projectDto.getEmployees());
			}
			return project;
		} else {
			return null;
		}
	}

	public static List<ProjectDto> toProjectListDto(List<Project> list) {
		ArrayList<ProjectDto> projectDto = new ArrayList<>();
		if (list != null) {
			for (Project project : list) {
				projectDto.add(toProjectDto(project));
			}
			return projectDto;
		} else {
			return projectDto;
		}
	}
}
