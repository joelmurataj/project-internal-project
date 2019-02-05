package com.amd.internal.project.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.amd.internal.project.dto.UserDto;

public interface UserService {

	public UserDto findByEmail(String email);
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
