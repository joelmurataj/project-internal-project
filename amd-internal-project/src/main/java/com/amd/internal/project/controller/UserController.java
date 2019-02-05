package com.amd.internal.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amd.internal.project.dto.UserDto;
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
}
