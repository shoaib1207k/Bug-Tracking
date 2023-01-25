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
import com.cg.bugtracking.dao.UserRepository;
import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.entity.User;
import com.cg.bugtracking.exception.IdAlreadyExistsException;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.exception.NotAdminException;

@Service
public class AdminServiceImpl implements AdminService {

	private static final Logger LOG = LogManager.getLogger(AdminServiceImpl.class);
	private static final String NO_ADMIN_FOUND = "Admin ID not found.";
	private static final String NO_USER_FOUND = "User ID not found.";
	private static final String ADMIN_ROLE_REQD = "Admin role is required.";
	private static final String ADMIN_EXISTS = "Admin ID already exists";
	private static final String NOT_ADMIN = "You are not an Admin!";

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AdminRepository aRepo;

	@Autowired
	private UserRepository uRepo;

	@Override
	public AdminDTO createAdmin(AdminDTO adminDto)
			throws NoSuchUserFoundException, NoAdminRoleFoundException, IdAlreadyExistsException {
		Optional<User> find = uRepo.findById(adminDto.getAdminId());
		if (aRepo.existsById(adminDto.getAdminId())) {
			throw new IdAlreadyExistsException(ADMIN_EXISTS);
		} else {
			if (find.isPresent()) {
				if (find.get().checkAdmin()) {
					Admin admin = modelMapper.map(adminDto, Admin.class);
					LOG.info("Saving admin");
					aRepo.save(admin);
					LOG.info("Saved. Returning admin");
					return adminDto;
				} else {
					LOG.error(ADMIN_ROLE_REQD);
					throw new NoAdminRoleFoundException(ADMIN_ROLE_REQD);
				}
			} else {
				LOG.error(NO_USER_FOUND);
				throw new NoSuchUserFoundException(NO_USER_FOUND);
			}
		}
	}

	@Override
	public List<AdminDTO> findAllAdmins(long adminId) throws NotAdminException {
		Optional<Admin> findAdmin = aRepo.findById(adminId);
		if (findAdmin.isPresent()) {
			LOG.info("Returning all admins");
			return aRepo.findAll().stream().map(adm -> modelMapper.map(adm, AdminDTO.class))
					.collect(Collectors.toList());
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}

	@Override
	public AdminDTO findAdminById(long id, long adminId) throws NoSuchAdminFoundException, NotAdminException {
		if (aRepo.existsById(adminId)) {
			Optional<Admin> adm = aRepo.findById(id);
			if (adm.isPresent()) {
				LOG.info("Returning admin using id");
				return modelMapper.map(adm.get(), AdminDTO.class);
			} else {
				LOG.error(NO_ADMIN_FOUND);
				throw new NoSuchAdminFoundException(NO_ADMIN_FOUND);
			}
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}

	@Override
	public AdminDTO updateAdmin(long id, AdminDTO adminDto, long adminId)
			throws NoSuchAdminFoundException, NoAdminRoleFoundException, NotAdminException {
		Optional<Admin> admUpdate = aRepo.findById(id);
		Admin admin = modelMapper.map(adminDto, Admin.class);
		if (aRepo.existsById(adminId)) {
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
				LOG.error(NO_ADMIN_FOUND);
				throw new NoSuchAdminFoundException(NO_ADMIN_FOUND);
			}
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}

	@Override
	public AdminDTO deleteAdmin(long id, long adminId) throws NoSuchAdminFoundException, NotAdminException {
		Optional<Admin> admDel = aRepo.findById(id);
		if (aRepo.existsById(adminId)) {
			if (admDel.isPresent()) {
				LOG.info("Deleting...");
				aRepo.delete(admDel.get());
				LOG.info("Deleted.");
			} else {
				LOG.error(NO_ADMIN_FOUND);
				throw new NoSuchAdminFoundException(NO_ADMIN_FOUND);
			}
			return modelMapper.map(admDel.get(), AdminDTO.class);
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}

}