package com.amd.internal.project.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="project_employee")
public class ProjectEmployee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date_employee")
	private Date startDateEmployee;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "finished_date_employee")
	private Date finishedDateEmployee;
	
	@Column(name = "is_activated", nullable = false)
	private boolean activated;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
