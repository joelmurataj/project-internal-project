package com.amd.internal.project.converter;

import java.util.ArrayList;
import java.util.List;

import com.amd.internal.project.dto.ProjectEmployeeDto;
import com.amd.internal.project.entity.Project;
import com.amd.internal.project.entity.ProjectEmployee;
import com.amd.internal.project.entity.User;

public class ProjectEmployeeConverter {

	public static ProjectEmployeeDto toProjectEmployeeDto(ProjectEmployee projectEmployee) {
		ProjectEmployeeDto projectEmployeeDto = new ProjectEmployeeDto();
		if (projectEmployee.getUser() != null) {
			projectEmployeeDto.setUserId(projectEmployee.getUser().getId());
		}
		if (projectEmployee.getProject() != null) {
			projectEmployeeDto.setProjectId(projectEmployee.getProject().getId());
		}
		projectEmployeeDto.setStartDateEmployee(projectEmployee.getStartDateEmployee());
		projectEmployeeDto.setFinishedDateEmployee(projectEmployee.getFinishedDateEmployee());
		projectEmployeeDto.setActivated(projectEmployee.isActivated());
		projectEmployeeDto.setAllocation(projectEmployee.getAllocation());

		return projectEmployeeDto;
	}

	public static ProjectEmployee toEmploeeProject(ProjectEmployeeDto employeeProjectDto) {
		if (employeeProjectDto != null) {
			ProjectEmployee projectemployee = new ProjectEmployee();

			projectemployee.setActivated(employeeProjectDto.isActivated());
			projectemployee.setFinishedDateEmployee(employeeProjectDto.getFinishedDateEmployee());
			projectemployee.setStartDateEmployee(employeeProjectDto.getStartDateEmployee());
			projectemployee.setAllocation(employeeProjectDto.getAllocation());
			User user = new User();
			user.setId(employeeProjectDto.getUserId());
			projectemployee.setUser(user);
			Project project = new Project();
			project.setId(employeeProjectDto.getProjectId());
			projectemployee.setProject(project);
			return projectemployee;
		} else {
			return null;
		}
	}

	public static List<ProjectEmployeeDto> toProjectEmployeeListDto(List<ProjectEmployee> list) {
		ArrayList<ProjectEmployeeDto> projectDto = new ArrayList<ProjectEmployeeDto>();
		if (list != null) {
			for (ProjectEmployee projectEmployee : list) {
				projectDto.add(toProjectEmployeeDto(projectEmployee));
			}
			return projectDto;
		} else {
			return projectDto;
		}
	}
}
