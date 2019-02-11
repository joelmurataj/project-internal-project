package com.amd.internal.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amd.internal.project.converter.UserConverter;
import com.amd.internal.project.dao.UserDao;
import com.amd.internal.project.dto.UserDto;
import com.amd.internal.project.entity.Project;
import com.amd.internal.project.entity.Role;
import com.amd.internal.project.entity.User;
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
	public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto userDto = UserConverter.toJwtUser(userDao.findByEmail(username));
		if (userDto == null) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return userDto;
	}

	@Override
	public List<UserDto> findByProjectId(int id) {
		return UserConverter.toUserListDto(userDao.findByProjectId(id));
	}

	@Override
	public List<UserDto> findByRole() {
		Role role = new Role();
		role.setId(2);
		Project project = new Project();
		project = null;
		return UserConverter.toUserListDto(userDao.findByRoleAndProject(role, project));
	}

	@Override
	public UserDto updateUser(Integer projectId, String email) {
		User user = userDao.findByEmail(email);
		Project project = new Project();
		if (projectId != null) {
			project.setId(projectId);
		}
		else {
			project=null;
		}
		user.setProject(project);
		userDao.saveAndFlush(user);
		return UserConverter.toUserDto(user);
	}

}
