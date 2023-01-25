package com.cg.bugtracking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;
import com.cg.bugtracking.exception.NotAdminException;
import com.cg.bugtracking.service.ProjectService;



@RestController

@RequestMapping("/{adminId}/project")

public class ProjectController {

	@Autowired
	private ProjectService prjService;
	
	
	@GetMapping
	public ResponseEntity<List<ProjectDTO>> getAllProjects(@PathVariable("adminId") long adminId)throws NotAdminException {
		return new ResponseEntity<>(prjService.getAllProjects(adminId), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProjectDTO> getProjectById(@PathVariable("id") long id, @PathVariable("adminId") long adminId) throws NoSuchProjectFoundException, NotAdminException{
		return new ResponseEntity<>(prjService.getProjectById(id,adminId), HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProjectDTO> updateProject(@Valid @PathVariable("id")long id, @RequestBody ProjectDTO prjDTO, @PathVariable("adminId") long adminId) throws NoSuchProjectFoundException, NotAdminException{
		return new ResponseEntity<>(prjService.updateProject(id, prjDTO,adminId), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ProjectDTO> deleteProject(@PathVariable("id")long id, @PathVariable("adminId") long adminId) throws NoSuchProjectFoundException,NotAdminException{
		return new ResponseEntity<>(prjService.deleteProject(id, adminId), HttpStatus.OK);
	}
}
