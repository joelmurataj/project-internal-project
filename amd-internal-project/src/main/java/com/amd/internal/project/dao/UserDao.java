package com.amd.internal.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amd.internal.project.entity.Project;
import com.amd.internal.project.entity.Role;
import com.amd.internal.project.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{
	public User findByEmail(String email);
	public List<User> findByProjectId(int id);
	public List<User> findByRoleAndProject(Role role, Project project);
}
