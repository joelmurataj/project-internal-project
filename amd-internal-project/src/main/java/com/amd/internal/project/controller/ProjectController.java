package com.amd.internal.project.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amd.internal.project.dto.ProjectDto;
import com.amd.internal.project.dto.UserDto;
import com.amd.internal.project.entity.Project;
import com.amd.internal.project.service.ProjectService;

@RestController
@CrossOrigin(origins = "${urlString}")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projects", method = RequestMethod.GET)
	public List<ProjectDto> getAllProject() {

		return projectService.findAll();
	}

	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projects/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProjectDto> deleteById(@PathVariable int id) {
		ProjectDto project = projectService.deleteProject(id);
		return new ResponseEntity<ProjectDto>(project, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projects/update/{id}", method = RequestMethod.GET)
	public ProjectDto getProject(@PathVariable int id) {
		return projectService.findById(id);
	}

	// @PreAuthorize("hasAuthority('employee')")
	@RequestMapping(path = "/project/{id}", method = RequestMethod.GET)
	public ProjectDto retrieveProject(@PathVariable int id) {
		return projectService.findById(id);
	}

	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projects/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ProjectDto> updateProject(@PathVariable int id, @RequestBody ProjectDto project) {
		if (id != 0) {
			projectService.updateProject(project, id);
		} else {
			projectService.save(project);
		}
		return new ResponseEntity<ProjectDto>(project, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projects/searchByTitle/{name}", method = RequestMethod.GET)
	public List<ProjectDto> searchProjectByName(@PathVariable String name) {
		return projectService.searchByName(name);
	}

	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "projectInfo/{id}", method = RequestMethod.GET)
	public Set<UserDto> getEmployeesOfProject(@PathVariable int id) {

		return projectService.getEmployeesOfProject(id);
	}

}
