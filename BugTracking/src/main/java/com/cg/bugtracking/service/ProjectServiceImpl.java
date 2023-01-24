package com.cg.bugtracking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bugtracking.dao.AdminRepository;
import com.cg.bugtracking.dao.ProjectRepository;
import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.entity.Project;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;

@Service
public class ProjectServiceImpl implements ProjectService {

	private static final Logger LOG = LogManager.getLogger(ProjectServiceImpl.class);
	private static final String NO_PROJECT_FOUND = "Project not found.";
	private static final String NO_ADMIN_FOUND = "Admin ID not found.";

	@Autowired
	private ProjectRepository pRepo;

	@Autowired
	private AdminRepository aRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProjectDTO createProject(ProjectDTO prjDTO) {
		Project prj = modelMapper.map(prjDTO, Project.class);
		LOG.info("Saving project");
		pRepo.save(prj);
		LOG.info("Saved Return project");
		return prjDTO;
	}

	@Override
	public ProjectDTO getProjectById(long prjId, long adminId) throws NoSuchProjectFoundException, NoSuchAdminFoundException {
		Optional<Project> prj = pRepo.findById(prjId);
		Optional<Admin> adm = aRepo.findById(adminId);
		if (adm.isPresent()) {
			if (prj.isPresent()) {
				LOG.info("Returning project using id");
				return modelMapper.map(prj.get(), ProjectDTO.class);
			} else {
				throw new NoSuchProjectFoundException(NO_PROJECT_FOUND);
			}
		}else {
			throw new NoSuchAdminFoundException(NO_ADMIN_FOUND);
		}
	}

	@Override
	public List<ProjectDTO> getAllProjects() {
		LOG.info("Returning all projects");
		return pRepo.findAll().stream().map(prj -> modelMapper.map(prj, ProjectDTO.class)).collect(Collectors.toList());

	}

	@Override
	public ProjectDTO updateProject(long id, ProjectDTO pDTO) throws NoSuchProjectFoundException {

		Optional<Project> prjToUpdate = pRepo.findById(id);
		Project prj = modelMapper.map(pDTO, Project.class);

		if (prjToUpdate.isPresent()) {
			LOG.info("Project present. Updating...");
			prjToUpdate.get().setProjId(prj.getProjId());
			prjToUpdate.get().setProjName(prj.getProjName());
			prjToUpdate.get().setProjStatus(prj.getProjStatus());
			LOG.info("Saving...");
			pRepo.save(prjToUpdate.get());
			LOG.info("Saved. Returning project");
			return pDTO;
		} else {
			throw new NoSuchProjectFoundException(NO_PROJECT_FOUND);
		}
	}

	@Override
	public ProjectDTO deleteProject(long id) throws NoSuchProjectFoundException {
		Optional<Project> prjToDel = pRepo.findById(id);
		if (prjToDel.isPresent())
			pRepo.delete(prjToDel.get());
		else
			throw new NoSuchProjectFoundException(NO_PROJECT_FOUND);
		return modelMapper.map(prjToDel.get(), ProjectDTO.class);
	}

}
