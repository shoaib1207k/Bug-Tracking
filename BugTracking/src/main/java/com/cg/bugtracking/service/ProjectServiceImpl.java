package com.cg.bugtracking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bugtracking.dao.AdminRepository;
import com.cg.bugtracking.dao.ProjectRepository;
import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.entity.Project;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;
import com.cg.bugtracking.exception.NotAdminException;

@Service
public class ProjectServiceImpl implements ProjectService {

	private static final Logger LOG = LogManager.getLogger(ProjectServiceImpl.class);
	private static final String NO_PROJECT_FOUND = "Project not found.";
	private static final String NOT_ADMIN = "Admin ID not found.";

	@Autowired
	private ProjectRepository pRepo;

	@Autowired
	private AdminRepository aRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProjectDTO createProject(ProjectDTO prjDTO, long adminId) throws NotAdminException {
		Project prj = modelMapper.map(prjDTO, Project.class);
		Optional<Admin> findAdmin = aRepo.findById(adminId);
		if (findAdmin.isPresent()) {
			LOG.info("Saving project");
			pRepo.save(prj);
			LOG.info("Saved Return project");
			return prjDTO;
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}

	@Override
	public ProjectDTO getProjectById(long prjId, long adminId) throws NoSuchProjectFoundException, NotAdminException {
		Optional<Project> prj = pRepo.findById(prjId);
		Optional<Admin> adm = aRepo.findById(adminId);
		if (adm.isPresent()) {
			if (prj.isPresent()) {
				LOG.info("Returning project using id");
				return modelMapper.map(prj.get(), ProjectDTO.class);
			} else {
				throw new NoSuchProjectFoundException(NO_PROJECT_FOUND);
			}
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}
	
	

	@Override
	public List<ProjectDTO> getAllProjects(long adminId) throws NotAdminException {
		if (aRepo.existsById(adminId)) {
			LOG.info("Returning all projects");
			return pRepo.findAll().stream().map(prj -> modelMapper.map(prj, ProjectDTO.class))
					.collect(Collectors.toList());
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}

	}

	@Override
	public ProjectDTO updateProject(long id, ProjectDTO pDTO, long adminId)
			throws NoSuchProjectFoundException, NotAdminException {

		Optional<Project> prjToUpdate = pRepo.findById(id);
		Project prj = modelMapper.map(pDTO, Project.class);
		Optional<Admin> adm = aRepo.findById(adminId);
		if (adm.isPresent()) {
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
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}

	@Override
	public ProjectDTO deleteProject(long id, long adminId) throws NoSuchProjectFoundException, NotAdminException {
		Optional<Project> prjToDel = pRepo.findById(id);
		if (aRepo.existsById(adminId)) {
			if (prjToDel.isPresent()) {
				LOG.info("Deleting...");
				pRepo.delete(prjToDel.get());
				LOG.info("Deleted.");
			} else
				throw new NoSuchProjectFoundException(NO_PROJECT_FOUND);
			return modelMapper.map(prjToDel.get(), ProjectDTO.class);
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}

	}

	@Override
	public List<ProjectDTO> getProjectByEmployeId(long empId,long adminId) throws NotAdminException  {
		if (aRepo.existsById(adminId)) {
			return modelMapper.map(pRepo.getProjectByEmployeId(empId),new TypeToken<List<Project>>(){}.getType());
		}else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}
}
