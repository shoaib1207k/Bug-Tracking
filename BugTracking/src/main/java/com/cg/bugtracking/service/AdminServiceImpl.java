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
import com.cg.bugtracking.dao.BugRepository;
import com.cg.bugtracking.dao.EmployeeRepository;
import com.cg.bugtracking.dao.ProjectRepository;
import com.cg.bugtracking.dao.UserRepository;
import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.dto.BugDTO;
import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.entity.Bug;
import com.cg.bugtracking.entity.Employee;
import com.cg.bugtracking.entity.Project;
import com.cg.bugtracking.entity.User;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;

@Service
public class AdminServiceImpl implements AdminService {

	private static final Logger LOG = LogManager.getLogger(AdminServiceImpl.class);
	private static final String NO_ADMIN_FOUND = "Admin ID not found.";
	private static final String NO_USER_FOUND = "User ID not found.";

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AdminRepository aRepo;

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private EmployeeRepository eRepo;

	@Autowired
	private BugRepository bRepo;

	@Autowired
	private ProjectRepository pRepo;

	@Override
	public AdminDTO createAdmin(AdminDTO adminDto) throws NoSuchUserFoundException, NoAdminRoleFoundException {
		Optional<User> find = uRepo.findById(adminDto.getAdminId());
		if (find.isPresent()) {
			if (find.get().checkAdmin()) {
				Admin admin = modelMapper.map(adminDto, Admin.class);
				LOG.info("Saving admin");
				aRepo.save(admin);
				LOG.info("Saved. Returning admin");
				return adminDto;
			} else {
				throw new NoAdminRoleFoundException("Admin role is required.");
			}
		} else {
			throw new NoSuchUserFoundException(NO_USER_FOUND);
		}
	}

	@Override
	public List<AdminDTO> findAllAdmins() {
		LOG.info("Returning all admins");
		return aRepo.findAll().stream().map(adm -> modelMapper.map(adm, AdminDTO.class)).collect(Collectors.toList());
	}

	@Override
	public AdminDTO findAdminById(long id) throws NoSuchAdminFoundException {
		Optional<Admin> adm = aRepo.findById(id);
		if (adm.isPresent()) {
			LOG.info("Returning admin using id");
			return modelMapper.map(adm.get(), AdminDTO.class);
		} else {
			throw new NoSuchAdminFoundException(NO_ADMIN_FOUND);
		}
	}

	@Override
	public AdminDTO updateAdmin(long id, AdminDTO adminDto)
			throws NoSuchAdminFoundException, NoAdminRoleFoundException {
		Optional<Admin> admUpdate = aRepo.findById(id);
		Admin admin = modelMapper.map(adminDto, Admin.class);
		if (admUpdate.isPresent()) {
			LOG.info("Admin present. Updating...");
			admUpdate.get().setAdminName(admin.getAdminName());
			admUpdate.get().setAdminId(admin.getAdminId());
			admUpdate.get().setAdminContact(admin.getAdminContact());
			LOG.info("Saving...");
			aRepo.save(admUpdate.get());
			LOG.info("Saved. Returning admin");
			return adminDto;
		} else {
			throw new NoSuchAdminFoundException(NO_ADMIN_FOUND);
		}
	}

	@Override
	public AdminDTO deleteAdmin(long id) throws NoSuchAdminFoundException {
		Optional<Admin> admDel = aRepo.findById(id);
		if (admDel.isPresent())
			aRepo.delete(admDel.get());
		else
			throw new NoSuchAdminFoundException(NO_ADMIN_FOUND);
		return modelMapper.map(admDel.get(), AdminDTO.class);
	}

	// employee

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO empDTO) {
		Employee emp = modelMapper.map(empDTO, Employee.class);
		eRepo.save(emp);
		return empDTO;
	}

	// project

	@Override
	public ProjectDTO createProject(ProjectDTO prjDTO) {
		Project prj = modelMapper.map(prjDTO, Project.class);
		pRepo.save(prj);
		return prjDTO;
	}

	// bug

	@Override
	public BugDTO createBug(BugDTO bugDTO) {
		Bug bug = modelMapper.map(bugDTO, Bug.class);
		bRepo.save(bug);
		return bugDTO;
	}

}