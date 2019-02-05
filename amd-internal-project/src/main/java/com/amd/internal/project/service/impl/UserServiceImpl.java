package com.amd.internal.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amd.internal.project.converter.UserConverter;
import com.amd.internal.project.dao.UserDao;
import com.amd.internal.project.dto.UserDto;
import com.amd.internal.project.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDto findByEmail(String email) {
		return UserConverter.toUserDto(userDao.findByEmail(email));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto userDto = UserConverter.toJwtUser(userDao.findByEmail(username));

		if (userDto == null) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return userDto;
	}

}
