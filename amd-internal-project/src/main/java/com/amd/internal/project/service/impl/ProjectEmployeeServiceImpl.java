package com.amd.internal.project.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amd.internal.project.converter.ProjectEmployeeConverter;
import com.amd.internal.project.dao.ProjectEmployeeDao;
import com.amd.internal.project.dto.ProjectEmployeeDto;
import com.amd.internal.project.entity.ProjectEmployee;
import com.amd.internal.project.service.ProjectEmployeeService;

@Service
public class ProjectEmployeeServiceImpl implements ProjectEmployeeService {

	@Autowired
	ProjectEmployeeDao projectEmployeeDao;

	@Override
	@Transactional
	public void save(ProjectEmployeeDto projectEmployeeDto) {
		if (projectEmployeeDto != null) {
			projectEmployeeDao.save(ProjectEmployeeConverter.toEmploeeProject(projectEmployeeDto));
		}
	}

	@Override
	public void findById(int projetId, int userId) {
		projectEmployeeDao.getProjectEmployee(projetId, userId);
		
	}

	@Override
	public void remove(int projectId, int userId) {
		projectEmployeeDao.remove(projectId, userId);
	}

}
