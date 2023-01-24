package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;

public interface ProjectService {

	public ProjectDTO createProject(ProjectDTO prjDTO);
	public ProjectDTO getProjectById(long prjId,long adminId) throws NoSuchProjectFoundException, NoSuchAdminFoundException;
	public List<ProjectDTO> getAllProjects();
	public ProjectDTO updateProject(long id, ProjectDTO pDTO) throws NoSuchProjectFoundException;
	public ProjectDTO deleteProject(long id) throws NoSuchProjectFoundException;
	
}
