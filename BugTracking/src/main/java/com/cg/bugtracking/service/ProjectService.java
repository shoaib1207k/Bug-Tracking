package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.entity.Project;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;

public interface ProjectService {

	public ProjectDTO createProject(ProjectDTO prjDTO);
	public ProjectDTO getProjectById(long prjId) throws NoSuchProjectFoundException;
	public List<ProjectDTO> getAllProjects();
	public ProjectDTO updateProject(long id, ProjectDTO pDTO) throws NoSuchProjectFoundException;
	public ProjectDTO deleteProject(long id) throws NoSuchProjectFoundException;
	
}
