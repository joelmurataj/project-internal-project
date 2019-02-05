package com.amd.internal.project.controller;

import java.util.List;

//import org.ocpsoft.rewrite.annotation.Join;
//import org.ocpsoft.rewrite.el.ELBeanName;
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
import com.amd.internal.project.entity.Project;
import com.amd.internal.project.service.ProjectService;

//@Scope(value = "session")
//@Component(value = "productController")
//@ELBeanName(value = "productController")
//@Join(path = "/project", to = "/project-form.jsf")
@RestController
@CrossOrigin(origins = "${urlString}")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	// @PreAuthorize("hasRole('manager')")
	// @Secured("managerax")
	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projects", method = RequestMethod.GET)
	public List<Project> getAllProject() {
		return projectService.findAll();
	}

	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projects/{id}", method = RequestMethod.GET)
	public ResponseEntity<Void> deleteById(@PathVariable int id) {
		Project project = projectService.deleteProject(id);
		if (project != null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projects/update/{id}", method = RequestMethod.GET)
	public ProjectDto getProject(@PathVariable int id) {
		return projectService.findById(id);
	}
	
	@PreAuthorize("hasAuthority('employee')")
	@RequestMapping(path = "/project/{id}", method = RequestMethod.GET)
	public ProjectDto retrieveProject(@PathVariable int id) {
		return projectService.findById(id);
	}

	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projects/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Project> updateProject(@PathVariable int id, @RequestBody Project project) {
		if (id != 0) {
			projectService.updateProject(project);
		} else {
			project.setFlag(true);
			projectService.save(project);
		}
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('manager')")
	@RequestMapping(path = "/projects", method = RequestMethod.POST)
	public void createProject(@RequestBody Project project) {
		projectService.save(project);
		// Uri
		// uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(project.getId()).toUri();
		// return ResponseEntity.created(uri).build();
	}
}
