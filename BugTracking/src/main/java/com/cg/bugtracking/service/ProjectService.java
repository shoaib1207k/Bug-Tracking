package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;
import com.cg.bugtracking.exception.NotAdminException;

public interface ProjectService {

	public ProjectDTO createProject(ProjectDTO prjDTO ,long adminId)throws NotAdminException;
	public ProjectDTO getProjectById(long prjId,long adminId) throws NoSuchProjectFoundException, NotAdminException;
	public List<ProjectDTO> getAllProjects(long adminId) throws NotAdminException;
	public ProjectDTO updateProject(long id, ProjectDTO pDTO, long adminId) throws NoSuchProjectFoundException,NotAdminException;
	public ProjectDTO deleteProject(long id, long adminId) throws NoSuchProjectFoundException, NotAdminException ;
	public List<ProjectDTO> getProjectByEmployeId(long empId,long adminId) throws NotAdminException;
}
