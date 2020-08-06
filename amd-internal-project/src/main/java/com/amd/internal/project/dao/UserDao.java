package com.amd.internal.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amd.internal.project.entity.Role;
import com.amd.internal.project.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{
	public User findByEmailAndIsActivated(String email, boolean isActivated);
//	public List<User> findByProjectId(int id);
	public List<User> findByRoleAndIsActivated(Role role, boolean isActivated);
	
	public List<User> findByIsActivatedAndRole(boolean isActivated,Role role);
	
	@Query("Select user from User user where user.firstName like :firstName% and user.isActivated=1 and user.role.id= :role and user.rank.id= :rank")
	public List<User> filterUsers(int role, int rank, String firstName);
	
	@Query("Select user from User user where user.firstName like :firstName% and user.isActivated=1 and user.role.id= :role")
	public List<User> filterUsersWithoutRank(int role, String firstName);
}
