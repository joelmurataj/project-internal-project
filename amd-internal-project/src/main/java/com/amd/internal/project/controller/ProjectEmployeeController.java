package com.amd.internal.project.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amd.internal.project.dto.ProjectDto;
import com.amd.internal.project.dto.ProjectEmployeeDto;
import com.amd.internal.project.dto.UserDto;
import com.amd.internal.project.service.ProjectEmployeeService;

@RestController
@CrossOrigin(origins = "${urlString}")
public class ProjectEmployeeController {

	@Autowired
	ProjectEmployeeService projectEmployeeService;

	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projectEmployee", method = RequestMethod.PUT)
	public ResponseEntity<ProjectEmployeeDto> updateProject(@RequestBody ProjectEmployeeDto projectEmployee) {
		projectEmployeeService.save(projectEmployee);

		return new ResponseEntity<ProjectEmployeeDto>(projectEmployee, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projectOfEmployee/{userId}/{projectId}", method = RequestMethod.PUT)
	public ResponseEntity<ProjectEmployeeDto> updateProject(@PathVariable int userId, @PathVariable Integer projectId) {
		if (projectId == 0) {
			projectId = null;
		}
		projectEmployeeService.remove(projectId, userId);
		return new ResponseEntity<ProjectEmployeeDto>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projectEmployee/update/{projectId}", method = RequestMethod.PUT)
	public ResponseEntity<ProjectEmployeeDto> updateDateOfUserInTheProject(@PathVariable int projectId,
			@RequestBody UserDto userDto) {
		ProjectEmployeeDto projectEmployee = new ProjectEmployeeDto();
		projectEmployee.setProjectId(projectId);
		projectEmployee.setUserId(userDto.getId());
		projectEmployee.setStartDateEmployee(userDto.getStartDateInProject());
		projectEmployee.setFinishedDateEmployee(userDto.getFinishedDateInProject());
		projectEmployeeService.update(projectEmployee);
		return new ResponseEntity<ProjectEmployeeDto>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projectEmployee/filter/{firsName}/{startDate}/{finishedDate}", method = RequestMethod.GET)
	public List<UserDto> filterProjects(@PathVariable String firsName, @PathVariable String startDate,
			@PathVariable String finishedDate) {
		if(firsName==null) {
			firsName="";
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date date1= null;
		try {
			System.out.println("po");
			date = format.parse(startDate);
			date1 = format.parse(finishedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return projectEmployeeService.filterByNameAndDates(firsName, date, date1);
	}
	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projectEmployee/filter/{startDate}/{finishedDate}", method = RequestMethod.GET)
	public List<UserDto> filterProjectsWithoutFirsName(@PathVariable String startDate,
			@PathVariable String finishedDate) {
		String firsName="";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date date1= null;
		try {
			date = format.parse(startDate);
			date1 = format.parse(finishedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return projectEmployeeService.filterByNameAndDates(firsName, date, date1);
	}
}
