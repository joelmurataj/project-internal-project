package com.amd.internal.project.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.amd.internal.project.dao.ProjectEmployeeDao;
import com.amd.internal.project.entity.ProjectEmployee;

@Repository
public class ProjectEmployeeDaoImpl implements ProjectEmployeeDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	@Transactional
	public void save(ProjectEmployee projectEmployee) {

		entityManager.persist(projectEmployee);
	}

	@Override
	@Transactional
	public ArrayList<ProjectEmployee> getProjectEmployee(int projectId, int userId) {
		ArrayList<ProjectEmployee> listOfProjectEmployee = null;
		try {
			listOfProjectEmployee = (ArrayList<ProjectEmployee>) entityManager.createQuery(
					"select projectEmployee from ProjectEmployee projectEmployee where projectEmployee.project.id =: projectId and projectEmployee.user.id =: userId",
					ProjectEmployee.class).setParameter("projectId", projectId).setParameter("userId", userId)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfProjectEmployee;
	}

	@Override
	@Transactional
	public void remove(int projectId, int userId) {
		try {
			entityManager
					.createQuery("update ProjectEmployee projectEmployee " + "set projectEmployee.activated=0 "
							+ "where projectEmployee.project.id =: projectId and projectEmployee.user.id =: userId")
					.setParameter("projectId", projectId).setParameter("userId", userId).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional
	public ArrayList<ProjectEmployee> projectConflictDateWithEmployee(Date startDate, Date finishedDate) {
		ArrayList<ProjectEmployee> listOfProjectEmployee = null;
		try {
			listOfProjectEmployee = (ArrayList<ProjectEmployee>) entityManager.createQuery(
					"select projectEmployee "
					+ "from ProjectEmployee projectEmployee "
					+ "where (projectEmployee.startDateEmployee <: startDate or projectEmployee.startDateEmployee >: finishedDate "
					+ "or projectEmployee.finishedDateEmployee <: startDate or projectEmployee.finishedDateEmployee >: finishedDate) and projectEmployee.activated =1",
					ProjectEmployee.class).setParameter("startDate", startDate).setParameter("finishedDate", finishedDate)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfProjectEmployee;
	}
	
}
