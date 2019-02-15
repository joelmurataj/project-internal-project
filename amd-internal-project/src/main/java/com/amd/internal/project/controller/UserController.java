package com.amd.internal.project.controller;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amd.internal.project.dto.ProjectEmployeeDto;
import com.amd.internal.project.dto.UserDto;
import com.amd.internal.project.service.ProjectEmployeeService;
import com.amd.internal.project.service.UserService;

@RestController
@CrossOrigin(origins="${urlString}")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectEmployeeService projectEmployeeService;
	
	@RequestMapping(path = "/{email}", method = RequestMethod.GET)
    public UserDto getProject(@PathVariable String email) {
		UserDto userDto = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	return userDto;
    }
	
//	@RequestMapping(path = "projectInfo/{id}", method = RequestMethod.GET)
//    public List<UserDto> getEmployeesOfProject(@PathVariable int id) {
//    	return userService.findByProjectId(id);
//    }
	
	@RequestMapping(path = "employees", method = RequestMethod.GET)
    public Set<UserDto> getEmployees() {
    	return userService.findByRole();
    }
	
}
