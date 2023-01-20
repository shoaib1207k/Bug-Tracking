package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.entity.Project;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;

public interface ProjectService {

	public Project createProject(Project prj);
	public Project getProjectById(long prjId) throws NoSuchProjectFoundException;
	public List<Project> getAllProjects();
	public Project updateProject(long id, Project p) throws NoSuchProjectFoundException;
	public Project deleteProject(long id) throws NoSuchProjectFoundException;
	
}
