package com.cg.bugtracking.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bugtracking.dao.ProjectRepository;


import com.cg.bugtracking.entity.Project;

import com.cg.bugtracking.exception.NoSuchProjectFoundException;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository pRepo; 
	
	@Override
	public Project createProject(Project prj) {
	
		return pRepo.save(prj);
	}
	
	@Override
	public Project getProjectById(long prjId) throws NoSuchProjectFoundException {
		Optional<Project> prj = pRepo.findById(prjId);
		if(prj.isPresent())
			return prj.get();
		throw new NoSuchProjectFoundException("No Such project found");
	}

	@Override
	public List<Project> getAllProjects() {
		return pRepo.findAll();

	}
	@Override
	public Project updateProject(long id, Project p) throws NoSuchProjectFoundException {
		Optional<Project> prjToUpdate = pRepo.findById(id);
		if(prjToUpdate.isPresent()) {
			prjToUpdate.get().setProjId(p.getProjId());
			prjToUpdate.get().setProjName(p.getProjName());
			prjToUpdate.get().setProjManager(p.getProjManager());
			prjToUpdate.get().setProjStatus(p.getProjStatus());		
			
			return pRepo.save(prjToUpdate.get());
		}else {
			throw new NoSuchProjectFoundException("No project with this id");
		}
	}
	

	@Override
	public Project deleteProject(long id) throws NoSuchProjectFoundException {
		Optional<Project> prjToDel = pRepo.findById(id);
		if(prjToDel.isPresent()) 
			pRepo.delete(prjToDel.get());	
		else
			throw new NoSuchProjectFoundException("No project with this id");
		return prjToDel.get();
	}

	

	

	
	}

	

