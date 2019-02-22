package com.amd.internal.project.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.amd.internal.project.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class ProjectDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private int pricePerMonth;
	private Date startDate;
	private Date finishedDate;
	private boolean flag;
	private String projectDetail;
	private int departamentId;
	private String departamentName;
	private int maxOfEmployee;
	private int vacancy;
	private int statusId;
	private String statusName;
	private List<User> employees;
	private int allocationOfTheEmployee;
	private Date startDateOfEmployee;
	private Date finishedDateOfEmployee;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPricePerMonth() {
		return pricePerMonth;
	}
	public void setPricePerMonth(int pricePerMonth) {
		this.pricePerMonth = pricePerMonth;
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
	public String getProjectDetail() {
		return projectDetail;
	}
	public void setProjectDetail(String projectDetail) {
		this.projectDetail = projectDetail;
	}
	public Date getFinishedDate() {
		return finishedDate;
	}
	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}
	@JsonBackReference
	public List<User> getEmployees() {
		return employees;
	}
	public void setEmployees(List<User> employees) {
		this.employees = employees;
	}
	
	public int getDepartamentId() {
		return departamentId;
	}
	
	public void setDepartamentId(int departamentId) {
		this.departamentId = departamentId;
	}
	public String getDepartamentName() {
		return departamentName;
	}
	public void setDepartamentName(String departamentName) {
		this.departamentName = departamentName;
	}
	public int getMaxOfEmployee() {
		return maxOfEmployee;
	}
	public void setMaxOfEmployee(int maxOfEmployee) {
		this.maxOfEmployee = maxOfEmployee;
	}
	public int getVacancy() {
		return vacancy;
	}
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public int getAllocationOfTheEmployee() {
		return allocationOfTheEmployee;
	}
	public void setAllocationOfTheEmployee(int allocationOfTheEmployee) {
		this.allocationOfTheEmployee = allocationOfTheEmployee;
	}
	public Date getStartDateOfEmployee() {
		return startDateOfEmployee;
	}
	public void setStartDateOfEmployee(Date startDateOfEmployee) {
		this.startDateOfEmployee = startDateOfEmployee;
	}
	public Date getFinishedDateOfEmployee() {
		return finishedDateOfEmployee;
	}
	public void setFinishedDateOfEmployee(Date finishedDateOfEmployee) {
		this.finishedDateOfEmployee = finishedDateOfEmployee;
	}
	
}
