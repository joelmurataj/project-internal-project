package com.amd.internal.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amd.internal.project.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{
	public User findByEmail(String email);
	
}
