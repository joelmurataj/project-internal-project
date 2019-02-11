package com.amd.internal.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amd.internal.project.dto.UserDto;
import com.amd.internal.project.entity.Project;
import com.amd.internal.project.service.UserService;

@RestController
@CrossOrigin(origins="${urlString}")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "/{email}", method = RequestMethod.GET)
    public UserDto getProject(@PathVariable String email) {
    	return userService.findByEmail(email);
    }
	
	@RequestMapping(path = "projectInfo/{id}", method = RequestMethod.GET)
    public List<UserDto> getEmployeesOfProject(@PathVariable int id) {
    	return userService.findByProjectId(id);
    }
	
	@RequestMapping(path = "employees", method = RequestMethod.GET)
    public List<UserDto> getEmployees() {
		for(UserDto user : userService.findByRole()) {
			System.out.println(user.getId());
		}
    	return userService.findByRole();
    }
	
	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projectOfEmployee/{email}/{projectId}", method = RequestMethod.PUT)
	public ResponseEntity<UserDto> updateProject(@PathVariable String email, @PathVariable Integer projectId) {
		if(projectId==0) {
			projectId=null;
		}
		UserDto user=userService.updateUser(projectId, email);
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}
}
