package com.amd.internal.project.service;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.amd.internal.project.dto.UserDto;

public interface UserService extends UserDetailsService{

	public UserDto findByEmail(String email);
	public UserDto loadUserByUsername(String username) throws UsernameNotFoundException;
//	public List<UserDto> findByProjectId(int id);
	public Set<UserDto> findByRole();
	public UserDto updateUser(Integer projectId,String email);
}
