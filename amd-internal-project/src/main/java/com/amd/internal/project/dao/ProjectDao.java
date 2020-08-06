package com.amd.internal.project.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amd.internal.project.entity.Departament;
import com.amd.internal.project.entity.Project;

@Repository
public interface ProjectDao extends JpaRepository<Project, Integer>{

	public List<Project> findByFlagAndDepartament(boolean flag, Departament departament,Sort sort);

	public Project findByName(String name);
	
	@Query("Select project from Project project where project.name like :name% and project.flag=1 and project.departament= :departament")
	public List<Project> searchByNameAndDepartament(String name, Departament departament);
}
