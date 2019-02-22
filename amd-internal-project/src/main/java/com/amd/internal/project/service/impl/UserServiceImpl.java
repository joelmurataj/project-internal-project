package com.amd.internal.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.amd.internal.project.converter.ProjectConverter;
import com.amd.internal.project.converter.UserConverter;
import com.amd.internal.project.dao.ProjectEmployeeDao;
import com.amd.internal.project.dao.UserDao;
import com.amd.internal.project.dto.ProjectDto;
import com.amd.internal.project.dto.UserDto;
import com.amd.internal.project.entity.Project;
import com.amd.internal.project.entity.ProjectEmployee;
import com.amd.internal.project.entity.Rank;
import com.amd.internal.project.entity.Role;
import com.amd.internal.project.entity.User;
import com.amd.internal.project.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProjectEmployeeDao projectemployeeDao;

	@Override
	public UserDto findByEmail(String email) {
		return UserConverter.toUserDto(userDao.findByEmail(email));
	}

	@Override
	@Transactional
	public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto userDto = UserConverter.toJwtUser(userDao.findByEmail(username));
		if (userDto == null) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return userDto;
	}

	@Override
	public List<UserDto> findByRole(ProjectDto projectDto) {
		Role role = new Role();
		role.setId(2);
		List<User> employees = userDao.findByRoleAndIsActivated(role, true);
		// HashMap<User, ArrayList<ProjectEmployee>> listOfEmployees = new HashMap<User,
		// ArrayList<ProjectEmployee>>();
		System.out.println(projectDto.getStartDate());
		System.out.println(projectDto.getFinishedDate());
		for (int i = 0; i < employees.size(); i++) {
			System.out.println(employees.get(i).getFirstName());
			List<ProjectEmployee> listOfProjectEmployee = projectemployeeDao.getProjectOfEmployee(
					employees.get(i).getId(), projectDto.getStartDate(), projectDto.getFinishedDate());
			int allocation = 0;
			for (int j = 0; j < listOfProjectEmployee.size(); j++) {
				allocation += listOfProjectEmployee.get(j).getAllocation();
				System.out.println(allocation + " " + employees.get(i).getFirstName());
			}
			if (allocation == 100) {
				employees.remove(i);
				i--;
			}
		}
		return UserConverter.toUserListDto(employees);
	}

	@Override
	@Transactional
	public void updateUser(UserDto userDto) {
		User user = userDao.findById(userDto.getId()).get();
		Rank rank = new Rank();
		rank.setIdRank(userDto.getRankId());
		user.setRank(rank);
	}

	@Override
	public List<UserDto> retrieveAllEmployees() {
		Role role = new Role();
		role.setId(2);
		List<UserDto> employees = UserConverter.toUserListDto(userDao.findByIsActivatedAndRole(true, role));
		for (int i = 0; i < employees.size(); i++) {
			List<ProjectEmployee> currentProject = projectemployeeDao
					.getCurrentProjectEmployee(employees.get(i).getId(), new Date());
			if (!currentProject.isEmpty()) {
				employees.get(i).setCurrentProject(currentProject.get(0).getProject().getName());
			}
		}
		return employees;
	}

	@Override
	@Transactional
	public UserDto saveUser(UserDto userDto) {
		UserDto user = findByEmail(userDto.getEmail());
		if (user == null) {
			UserDto userLogged = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			userDto.setPassword(encoder.encode(userDto.getPassword()));
			userDto.setDepartamentId(userLogged.getDepartamentId());
			userDto.setSuperiorId(userLogged.getId());
			userDto.setRoleId(2);
			userDao.save(UserConverter.toUser(userDto));
			return userDto;
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public UserDto removeUser(int id) {
		boolean exist = false;
		User user = userDao.findById(id).get();
		List<Project> projects = user.getProjects();
		if (user.getProjects() != null) {
			for (int i = 0; i < projects.size(); i++) {
				if (projects.get(i).isFlag()) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				user.setActivated(false);
				return UserConverter.toUserDto(user);
			}
		}
		return null;
	}

	@Override
	public UserDto getUser(int id) {
		UserDto userDto = UserConverter.toUserDto(userDao.findById(id).get());
		if (userDto.isFlag()) {
			List<ProjectEmployee> currentProject = projectemployeeDao.getCurrentProjectEmployee(userDto.getId(),
					new Date());
			if (!currentProject.isEmpty()) {
				userDto.setCurrentProject(currentProject.get(0).getProject().getName());
			}
			return userDto;
		} else {
			return null;
		}

	}

	@Override
	@Transactional
	public List<ProjectDto> getProjectsOfEmployee(int id) {
		List<ProjectDto> listOfProjectDto1 = new ArrayList<ProjectDto>();
		UserDto user = getUser(id);
		if (user != null) {
			List<ProjectDto> listOfProjectDto = ProjectConverter.toProjectListDto(user.getProjects());
			for (ProjectDto projectDto : listOfProjectDto) {
				ProjectEmployee listOfProjectEmployee = projectemployeeDao.getProjectEmployee(projectDto.getId(), id);
				if (listOfProjectEmployee != null) {
					projectDto.setStartDateOfEmployee(listOfProjectEmployee.getStartDateEmployee());
					projectDto.setFinishedDateOfEmployee(listOfProjectEmployee.getFinishedDateEmployee());
					projectDto.setAllocationOfTheEmployee(listOfProjectEmployee.getAllocation());
					listOfProjectDto1.add(projectDto);
				}
			}
		}
		return listOfProjectDto1;
	}

}
