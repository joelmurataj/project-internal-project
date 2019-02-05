//package com.amd.internal.project.dao.impl;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.stereotype.Repository;
//
//import com.amd.internal.project.dao.ProjectDao;
//import com.amd.internal.project.entity.Project;
//
//@Repository
//public class ProjectDaoImpl implements ProjectDao {
//
//	@PersistenceContext
//	EntityManager entityManager;
//
//	@Override
//	public Project findById(int projectId) {
//		Project project = null;
//		try {
//			project = entityManager.find(Project.class, projectId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return project;
//	}
//
//	@Override
//	public List<Project> findAll() {
//		List<Project> projects = null;
//		try {
//			projects = entityManager
//					.createQuery("select project from Project project where project.flag=1", Project.class)
//					.getResultList();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return projects;
//	}
//
//	@Override
//	public void save(Project project) {
//		try {
//			entityManager.persist(project);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void deleteProject(int projectId) {
//		try {
//			entityManager.createQuery("update Project project set project.flag=0 where project.id=:id")
//					.setParameter("id", projectId).executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void updateProject(Project project) {
//		try {
//			entityManager.merge(project);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
