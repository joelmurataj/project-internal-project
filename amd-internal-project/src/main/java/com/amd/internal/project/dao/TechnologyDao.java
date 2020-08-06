package com.amd.internal.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amd.internal.project.entity.Technology;

public interface TechnologyDao extends JpaRepository<Technology, Integer>{

	public List<Technology> findByIsActivated(boolean isActivated);
}
