package com.cg.bugtracking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bugtracking.dao.ProjectRepository;
import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.entity.Project;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository pRepo; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ProjectDTO createProject(Project prj) {
		return modelMapper.map(pRepo.save(prj), ProjectDTO.class);
	}

	@Override
	public ProjectDTO getProjectById(long prjId) throws NoSuchProjectFoundException {
		Optional<Project> prj = pRepo.findById(prjId);
		if(prj.isPresent())
			return modelMapper.map(prj.get(),ProjectDTO.class);
		throw new NoSuchProjectFoundException("No Such project found");
	}

	@Override
	public List<ProjectDTO> getAllProjects() {
		return pRepo.findAll().stream().map(prj->modelMapper.map(prj, ProjectDTO.class))
				.collect(Collectors.toList());

	}

	@Override
	public ProjectDTO updateProject(long id, Project p) throws NoSuchProjectFoundException {
		Project prjToUpdate = pRepo.findById(id).get();
		
		prjToUpdate.setProjId(p.getProjId());
		prjToUpdate.setProjName(p.getProjName());
		prjToUpdate.setProjManager(p.getProjManager());
		prjToUpdate.setProjStatus(p.getProjStatus());
		prjToUpdate.setBugList(p.getBugList());
		return modelMapper.map(pRepo.save(prjToUpdate), ProjectDTO.class);
	}

	@Override
	public ProjectDTO deleteProject(long id) throws NoSuchProjectFoundException {
		Optional<Project> prjToDel = pRepo.findById(id);
		if(prjToDel.isPresent()) 
			pRepo.delete(prjToDel.get());	
		else
			throw new NoSuchProjectFoundException("No project with this id");
		return modelMapper.map(prjToDel.get(), ProjectDTO.class);
	}

	
}
