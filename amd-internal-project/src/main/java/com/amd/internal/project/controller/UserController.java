package com.amd.internal.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amd.internal.project.dao.RankDao;
import com.amd.internal.project.dto.PasswordDto;
import com.amd.internal.project.dto.ProjectDto;
import com.amd.internal.project.dto.UserDto;
import com.amd.internal.project.entity.Rank;
import com.amd.internal.project.service.UserService;

@RestController
@CrossOrigin(origins="${urlString}")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RankDao rankDao;
	
	@RequestMapping(path = "/userLogged", method = RequestMethod.GET)
    public UserDto getProject() {
		UserDto userDto = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDto userLogged=userService.getUser(userDto.getId());
    	return userLogged;
    }
	
//	@RequestMapping(path = "projectInfo/{id}", method = RequestMethod.GET)
//    public List<UserDto> getEmployeesOfProject(@PathVariable int id) {
//    	return userService.findByProjectId(id);
//    }
	
	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "employees", method = RequestMethod.PUT)
    public List<UserDto> getEmployees(@RequestBody ProjectDto projectdto) {
		System.out.println(projectdto.getName());
    	return userService.findByRole(projectdto);
    }
	
	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/allEmployees", method = RequestMethod.GET)
    public List<UserDto> retrieveAllEmployees() {
    	return userService.retrieveAllEmployees();
    }
	
	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/allEmployees/filter", method = RequestMethod.PUT)
    public List<UserDto> filterEmployees(@RequestBody UserDto userDto) {
    	return userService.retrieveAllEmployees(userDto);
    }
	
	@RequestMapping(path = "/allRanks", method = RequestMethod.GET)
    public List<Rank> retrieveAllRanks() {
    	return rankDao.findAll();
    }
	
	@RequestMapping(path = "/updateUser", method = RequestMethod.PUT)
    public void updateUser(@RequestBody UserDto userDto) {
		System.out.println("po");
    	userService.updateUser(userDto);
    }
	
	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/addEmployee", method = RequestMethod.POST)
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
    	UserDto user=userService.saveUser(userDto);
    	return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }
	
	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/removeUser/{id}", method = RequestMethod.GET)
    public UserDto removeUser(@PathVariable int id) {
    	UserDto userDto=userService.removeUser(id);
    	return userDto;
    }
	
	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public UserDto getUser(@PathVariable int id) {
    	UserDto userDto = userService.getUser(id);
    	return userDto;
    }
	
	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/userInfo/{id}", method = RequestMethod.GET)
	public List<ProjectDto> getProjectsOfProject(@PathVariable int id) {

		return userService.getProjectsOfEmployee(id);
	}
	
	@PreAuthorize("hasAuthority('employee')")
	@RequestMapping(path = "/projectsOfUser", method = RequestMethod.GET)
	public List<ProjectDto> getProjectsOfUser() {
		UserDto userDto= (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.getProjectsOfEmployee(userDto.getId());
	}
	
	@RequestMapping(path = "/changePassword", method = RequestMethod.PUT)
	public PasswordDto changePassword( @RequestBody PasswordDto passwordDto) {
		return userService.changePassword(passwordDto);
	}
}
