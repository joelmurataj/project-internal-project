package com.amd.internal.project.controller;

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
		if(projectId==0) {
			projectId=null;
		}
		projectEmployeeService.remove(projectId, userId);
		return new ResponseEntity<ProjectEmployeeDto>(HttpStatus.OK);
	}
}
