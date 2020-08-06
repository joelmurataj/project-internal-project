package com.amd.internal.project.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public ProjectEmployee getActivatedProjectEmployee(int projectId, int userId) {
		ProjectEmployee listOfProjectEmployee = null;
		try {
			listOfProjectEmployee = (ProjectEmployee) entityManager
					.createQuery(
							"select projectEmployee from ProjectEmployee projectEmployee where "
									+ "projectEmployee.project.id =: projectId "
									+ "and projectEmployee.user.id =: userId and projectEmployee.activated =1",
							ProjectEmployee.class)
					.setParameter("projectId", projectId).setParameter("userId", userId).getSingleResult();
		} catch (Exception e) {
			return null;
		}
		return listOfProjectEmployee;
	}

	@Override
	public List<ProjectEmployee> getProjectOfEmployee(int userId, Date startdate, Date endDate) {
		List<ProjectEmployee> listOfProjectEmployee = (List<ProjectEmployee>) entityManager.createQuery(
				"select projectEmployee from ProjectEmployee projectEmployee where projectEmployee.user.id =: userId"
						+ " and (:startDate between projectEmployee.startDateEmployee and projectEmployee.finishedDateEmployee"
						+ " or :endDate between projectEmployee.startDateEmployee and projectEmployee.finishedDateEmployee)"
						+ " and projectEmployee.activated =1",
				ProjectEmployee.class).setParameter("userId", userId).setParameter("startDate", startdate)
				.setParameter("endDate", endDate).getResultList();
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
	public ArrayList<ProjectEmployee> projectConflictDateWithEmployee(int projectId, Date startDate,
			Date finishedDate) {
		ArrayList<ProjectEmployee> listOfProjectEmployee = null;
		try {
			listOfProjectEmployee = (ArrayList<ProjectEmployee>) entityManager.createQuery("select projectEmployee "
					+ "from ProjectEmployee projectEmployee "
					+ "where (projectEmployee.startDateEmployee <: startDate or projectEmployee.startDateEmployee >: finishedDate "
					+ "or projectEmployee.finishedDateEmployee <: startDate or projectEmployee.finishedDateEmployee >: finishedDate) and projectEmployee.project.id =: projectId and projectEmployee.activated =1",
					ProjectEmployee.class).setParameter("startDate", startDate)
					.setParameter("finishedDate", finishedDate).setParameter("projectId", projectId).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfProjectEmployee;
	}

	@Override
	@Transactional
	public ArrayList<ProjectEmployee> getAllProjectsEmployeeWithinTheDates(Date startDate, Date finishedDate) {
		ArrayList<ProjectEmployee> listOfProjectEmployee = null;
		try {
			listOfProjectEmployee = (ArrayList<ProjectEmployee>) entityManager.createQuery("select projectEmployee "
					+ "from ProjectEmployee projectEmployee "
					+ " and :startDate between projectEmployee.startDateEmployee and projectEmployee.finishedDateEmployee"
					+ " and :endDate between projectEmployee.startDateEmployee and projectEmployee.finishedDateEmployee and projectEmployee.activated =1",
					ProjectEmployee.class).setParameter("startDate", startDate)
					.setParameter("finishedDate", finishedDate).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfProjectEmployee;
	}

	@Override
	@Transactional
	public void update(ProjectEmployee projectEmployee) {
		try {
			entityManager.merge(projectEmployee);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public ArrayList<ProjectEmployee> filterByNameAndDates(String firstNameOfEmployee, Date startDate,
			Date finishedDate, int projectId) {
		ArrayList<ProjectEmployee> projectEmployee = (ArrayList<ProjectEmployee>) entityManager.createQuery(
				"from ProjectEmployee projectEmployee " + "where projectEmployee.startDateEmployee >= :startDate "
						+ "and projectEmployee.finishedDateEmployee <= :finishedDate "
						+ "and projectEmployee.user.firstName like :firstNameOfEmployee and projectEmployee.activated=1 and projectEmployee.project.id= :projectId",
				ProjectEmployee.class).setParameter("startDate", startDate).setParameter("finishedDate", finishedDate)
				.setParameter("firstNameOfEmployee", firstNameOfEmployee + "%").setParameter("projectId", projectId)
				.getResultList();
		return projectEmployee;
	}

	@Override
	@Transactional
	public List<ProjectEmployee> getCurrentProjectEmployee(int userId, Date date) {
		List<ProjectEmployee> listOfProjectEmployee = null;
		try {
			listOfProjectEmployee = (List<ProjectEmployee>) entityManager
					.createQuery("select projectEmployee from ProjectEmployee projectEmployee where"
							+ " :date between projectEmployee.startDateEmployee and projectEmployee.finishedDateEmployee"
							+ " and projectEmployee.user.id =: userId and projectEmployee.activated =1",
							ProjectEmployee.class)
					.setParameter("date", date).setParameter("userId", userId).setMaxResults(1).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfProjectEmployee;
	}

	@Override
	public ProjectEmployee getProjectEmployee(int projectId, int userId) {
		ProjectEmployee listOfProjectEmployee = null;
		try {
			listOfProjectEmployee = (ProjectEmployee) entityManager
					.createQuery("select projectEmployee from ProjectEmployee projectEmployee where "
							+ "projectEmployee.project.id =: projectId " + "and projectEmployee.user.id =: userId",
							ProjectEmployee.class)
					.setParameter("projectId", projectId).setParameter("userId", userId).getSingleResult();
		} catch (Exception e) {
			return null;
		}
		return listOfProjectEmployee;
	}

}
