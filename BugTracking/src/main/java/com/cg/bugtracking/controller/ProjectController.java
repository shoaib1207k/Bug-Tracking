package com.cg.bugtracking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;
import com.cg.bugtracking.service.ProjectService;



@RestController
public class ProjectController {

	@Autowired
	private ProjectService prjService;
	
	@PostMapping("/project")
	public ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody ProjectDTO prjDTO){
		return new ResponseEntity<>(prjService.createProject(prjDTO), HttpStatus.CREATED);
	}
	
	@GetMapping("/projects")
	public ResponseEntity<List<ProjectDTO>> getAllProjects(){
		return new ResponseEntity<>(prjService.getAllProjects(), HttpStatus.OK);
	}
	
	@GetMapping("/project/{id}")
	public ResponseEntity<ProjectDTO> getProjectById(@PathVariable("id") long id) throws NoSuchProjectFoundException{
		return new ResponseEntity<>(prjService.getProjectById(id), HttpStatus.FOUND);
	}
	
	@PostMapping("/update-project/{id}")
	public ResponseEntity<ProjectDTO> updateProject(@Valid @PathVariable("id")long id, @RequestBody ProjectDTO prjDTO) throws NoSuchProjectFoundException{
		return new ResponseEntity<>(prjService.updateProject(id, prjDTO), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-project/{id}")
	public ResponseEntity<ProjectDTO> deleteProject(@PathVariable("id")long id) throws NoSuchProjectFoundException{
		return new ResponseEntity<>(prjService.deleteProject(id), HttpStatus.OK);
	}
}
