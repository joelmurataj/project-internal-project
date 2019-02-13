package com.amd.internal.project.dao;

import java.util.ArrayList;
import java.util.Date;

import com.amd.internal.project.entity.ProjectEmployee;

public interface ProjectEmployeeDao {

	public void save(ProjectEmployee projectEmployee);
	public ArrayList<ProjectEmployee> getProjectEmployee(int projetId, int userId);
	
	public void remove(int projectId, int userId);
	public ArrayList<ProjectEmployee> projectConflictDateWithEmployee(Date projectId, Date userId);
}
