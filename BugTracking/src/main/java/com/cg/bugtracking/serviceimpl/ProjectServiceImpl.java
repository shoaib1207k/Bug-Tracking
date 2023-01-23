package com.cg.bugtracking.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bugtracking.dao.ProjectRepository;
import com.cg.bugtracking.dto.ProjectDTO;

import com.cg.bugtracking.entity.Project;
import com.cg.bugtracking.exception.NoSuchEmployeeFoundException;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;
import com.cg.bugtracking.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository pRepo; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ProjectDTO createProject(ProjectDTO prjDTO) {
		Project prj = modelMapper.map(prjDTO, Project.class);
		pRepo.save(prj);
		return prjDTO;
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
	public ProjectDTO updateProject(long id, ProjectDTO pDTO) throws NoSuchProjectFoundException {
		
		Optional<Project> prjToUpdate = pRepo.findById(id);
		Project prj = modelMapper.map(pDTO, Project.class);

		if(prjToUpdate.isPresent()) {
			prjToUpdate.get().setProjId(prj.getProjId());
			prjToUpdate.get().setProjName(prj.getProjName());
			prjToUpdate.get().setProjManager(prj.getProjManager());
			prjToUpdate.get().setProjStatus(prj.getProjStatus());		
			pRepo.save(prjToUpdate.get());
			return pDTO;
		}else {
			throw new NoSuchProjectFoundException("No project with this id");
		}
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

	

