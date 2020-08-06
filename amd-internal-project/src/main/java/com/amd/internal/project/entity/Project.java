package com.amd.internal.project.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="project")
public class Project implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 11)
	private int id;
	
	@Column(name = "name", nullable = false, length = 45)
	private String name;
	
	@Column(name = "price_per_month", nullable = false, length = 4)
	private int pricePerMonth;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "finished_date")
	private Date finishedDate;
	
	@Column(name = "max_of_employee", nullable = false)
	private int maxOfEmployee;
	
	@Column(name = "vacancy", nullable = false, length = 3)
	private int vacancy;
	
	@Column(name = "flag", nullable = false , length = 3)
	private boolean flag;
	
	@Column(name = "project_detail", nullable = false, length = 500)
	private String projectDetail;
	
	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "departament_id", nullable = false)
	private Departament departament;
	
	@ManyToMany(fetch= FetchType.LAZY)
	@JoinTable(
			name= "project_employee",
			joinColumns=@JoinColumn(name="project_id"),
			inverseJoinColumns=@JoinColumn(name="user_id"))
	private List<User> employees;
	
	@ManyToMany(fetch= FetchType.LAZY)
	@JoinTable(
			name= "project_technology",
			joinColumns=@JoinColumn(name="project_id"),
			inverseJoinColumns=@JoinColumn(name="technology_id"))
	private List<Technology> technologies;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getFinishedDate() {
		return finishedDate;
	}
	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}
	
	public List<User> getEmployees() {
		return employees;
	}
	public void setEmployees(List<User> employees) {
		this.employees = employees;
	}
	public Departament getDepartament() {
		return departament;
	}
	public void setDepartament(Departament departament) {
		this.departament = departament;
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public List<Technology> getTechnologies() {
		return technologies;
	}
	public void setTechnologies(List<Technology> technologies) {
		this.technologies = technologies;
	}
	
}
