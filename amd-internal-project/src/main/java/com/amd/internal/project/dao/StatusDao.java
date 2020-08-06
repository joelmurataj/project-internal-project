package com.amd.internal.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amd.internal.project.entity.Status;

public interface StatusDao extends JpaRepository<Status, Integer>{

	@Query("Select status from Status status where status.name like 'Done' or status.name like 'On going'")
	public List<Status> getEditableStatus();
}
