package com.amd.internal.project.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.amd.internal.project.dto.ProjectDto;
import com.amd.internal.project.dto.UserDto;

public interface UserService extends UserDetailsService{

	public UserDto findByEmail(String email);
	public UserDto loadUserByUsername(String username) throws UsernameNotFoundException;
//	public List<UserDto> findByProjectId(int id);
	public List<UserDto> findByRole(ProjectDto projectDto);
	public void updateUser(UserDto userDto);
	public UserDto saveUser(UserDto userDto);
	public List<UserDto> retrieveAllEmployees();
	public UserDto removeUser(int id);
	public UserDto getUser(int id);
	public List<ProjectDto> getProjectsOfEmployee(int id);
	public List<UserDto> retrieveAllEmployees(UserDto userDto);
}
