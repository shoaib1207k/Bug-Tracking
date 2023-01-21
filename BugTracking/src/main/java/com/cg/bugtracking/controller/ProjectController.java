package com.cg.bugtracking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cg.bugtracking.dto.ProjectDTO;

import com.cg.bugtracking.entity.Project;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;
import com.cg.bugtracking.service.ProjectService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ProjectController {

	@Autowired
	private ProjectService prjService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/project")
	public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO prjDTO){
		Project prj = modelMapper.map(prjDTO, Project.class);
		Project prjDB = prjService.createProject(prj);
		return new ResponseEntity<>(modelMapper.map(prjDB, ProjectDTO.class), HttpStatus.CREATED);
	}
	
	@GetMapping("/projects")
	public ResponseEntity<List<ProjectDTO>> getAllProjects(){
		List<Project> prjList = prjService.getAllProjects();
		List<ProjectDTO> prjDTOList = prjList.stream()
										.map(prj->modelMapper.map(prj, ProjectDTO.class))
										.collect(Collectors.toList());
		
		return new ResponseEntity<>(prjDTOList, HttpStatus.OK);
	}
	
	@GetMapping("/project/{id}")
	public ResponseEntity<ProjectDTO> getProjectById(@PathVariable("id") long id) throws NoSuchProjectFoundException{
		Project prj = prjService.getProjectById(id);
		ProjectDTO prjDTO = modelMapper.map(prj, ProjectDTO.class);
		return new ResponseEntity<>(prjDTO, HttpStatus.FOUND);
	}
	
	@PostMapping("/update-project/{id}")
	public ResponseEntity<ProjectDTO> updateProject(@PathVariable("id")long id, @RequestBody ProjectDTO prjDTO) throws NoSuchProjectFoundException{
		Project prjToUpdate = modelMapper.map(prjDTO, Project.class);
		prjService.updateProject(id, prjToUpdate);
		return new ResponseEntity<>(prjDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-project/{id}")
	public ResponseEntity<ProjectDTO> deleteProject(@PathVariable("id")long id) throws NoSuchProjectFoundException{
		Project prj = prjService.deleteProject(id);
		ProjectDTO prjDTO = modelMapper.map(prj, ProjectDTO.class);
		return new ResponseEntity<>(prjDTO, HttpStatus.OK);
	}
}
