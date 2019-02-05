package com.amd.internal.project.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class UserDto implements UserDetails {

	private static final long serialVersionUID = 5155720064139820502L;

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int departamentId;
	private int projectId;
	private int superiorId;
	private int roleId;
	private int pricePerHour;
	private String roleName;
	private boolean flag;
	private Date startDate;
	private List<SimpleGrantedAuthority> authorities;
	
	public UserDto() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		this.authorities = authorities;
	}

	@JsonIgnore
	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public int getDepartamentId() {
		return departamentId;
	}

	public int getProjectId() {
		return projectId;
	}

	//@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public int getSuperiorId() {
		return superiorId;
	}

	public int getRoleId() {
		return roleId;
	}

	public int getPricePerHour() {
		return pricePerHour;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public List<SimpleGrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDepartamentId(int departamentId) {
		this.departamentId = departamentId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public void setSuperiorId(int superiorId) {
		this.superiorId = superiorId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setPricePerHour(int pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
