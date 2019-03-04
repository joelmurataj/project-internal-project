package com.amd.internal.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="technology")
public class Technology implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 11)
	private int id;
	
	@Column(name = "technology_name", nullable = false, length = 45)
	private String technologyName;

	@Column(name = "is_activated", nullable = false)
	private boolean isActivated;
	
//	@ManyToMany(fetch= FetchType.LAZY)
//	@JoinTable(
//			name= "project_technology",
//			joinColumns=@JoinColumn(name="technology_id"),
//			inverseJoinColumns=@JoinColumn(name="project_id"))
//	private List<Project> projects ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTechnologyName() {
		return technologyName;
	}

	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

//	public List<Project> getProjects() {
//		return projects;
//	}
//
//	public void setProjects(List<Project> projects) {
//		this.projects = projects;
//	}
	
}
