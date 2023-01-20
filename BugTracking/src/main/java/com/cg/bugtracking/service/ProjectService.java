package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.entity.Project;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;

public interface ProjectService {

	public ProjectDTO createProject(Project prj);
	public ProjectDTO getProjectById(long prjId) throws NoSuchProjectFoundException;
	public List<ProjectDTO> getAllProjects();
	public ProjectDTO updateProject(long id, Project p) throws NoSuchProjectFoundException;
	public ProjectDTO deleteProject(long id) throws NoSuchProjectFoundException;
	
}
