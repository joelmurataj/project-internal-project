package com.amd.internal.project.dto;

import java.io.Serializable;
import java.util.Date;

public class ProjectEmployeeDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int projectId;
	private int userId;
	private Date startDateEmployee;
	private Date finishedDateEmployee;
	private boolean activated;
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getStartDateEmployee() {
		return startDateEmployee;
	}
	public void setStartDateEmployee(Date startDateEmployee) {
		this.startDateEmployee = startDateEmployee;
	}
	public Date getFinishedDateEmployee() {
		return finishedDateEmployee;
	}
	public void setFinishedDateEmployee(Date finishedDateEmployee) {
		this.finishedDateEmployee = finishedDateEmployee;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
}
