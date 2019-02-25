package com.amd.internal.project.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amd.internal.project.entity.ProjectEmployee;

public interface ProjectEmployeeDao {

	public void save(ProjectEmployee projectEmployee);

	public ProjectEmployee getProjectEmployee(int projetId, int userId);

	public void remove(int projectId, int userId);

	public ArrayList<ProjectEmployee> projectConflictDateWithEmployee(int projectId, Date startDate, Date finishedDate);

	public void update(ProjectEmployee projectEmployee);

	public ArrayList<ProjectEmployee> filterByNameAndDates(String firstNameOfEmployee, Date startDate,
			Date finishedDate, int projectId);

	public List<ProjectEmployee> getProjectOfEmployee(int userId, Date startdate, Date endDate);

	public ArrayList<ProjectEmployee> getAllProjectsEmployeeWithinTheDates(Date startDate, Date finishedDate);
	
	public List<ProjectEmployee> getCurrentProjectEmployee(int userId, Date date);

	public ProjectEmployee getActivatedProjectEmployee(int projectId, int userId);
}
