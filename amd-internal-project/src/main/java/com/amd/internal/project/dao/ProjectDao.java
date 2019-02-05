package com.amd.internal.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amd.internal.project.entity.Project;

@Repository
public interface ProjectDao extends JpaRepository<Project, Integer>{

	public List<Project> findByFlag(boolean flag);
}
