package com.cg.bugtracking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bugtracking.dao.AdminRepository;
import com.cg.bugtracking.dao.UserRepository;
import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.entity.User;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;

@Service
public class AdminServiceImpl implements AdminService {

	private static final String NO_ADMIN_FOUND = "Admin ID not found.";
	private static final String NO_USER_FOUND = "User ID not found.";

	@Autowired
	private AdminRepository aRepo;

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public AdminDTO createAdmin(AdminDTO adminDto) throws NoSuchUserFoundException, NoAdminRoleFoundException {
		Optional<User> find = uRepo.findById(adminDto.getAdminId());
		if (find.isPresent()) {
			if (find.get().checkAdmin()) {
				Admin admin = modelMapper.map(adminDto, Admin.class);
				aRepo.save(admin);
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
		return aRepo.findAll().stream().map(adm -> modelMapper.map(adm, AdminDTO.class)).collect(Collectors.toList());
	}

	@Override
	public AdminDTO findAdminById(long id) throws NoSuchAdminFoundException {
		Optional<Admin> adm = aRepo.findById(id);
		if (adm.isPresent())
			return modelMapper.map(adm.get(), AdminDTO.class);
		throw new NoSuchAdminFoundException(NO_ADMIN_FOUND);
	}

	@Override
	@Transactional
	public AdminDTO updateAdmin(long id, AdminDTO adminDto)
			throws NoSuchAdminFoundException, NoAdminRoleFoundException {
		Optional<Admin> admUpdate = aRepo.findById(id);
		Admin admin = modelMapper.map(adminDto, Admin.class);
		if (admUpdate.isPresent()) {
			admUpdate.get().setAdminName(admin.getAdminName());
			admUpdate.get().setAdminId(admin.getAdminId());
			admUpdate.get().setAdminContact(admin.getAdminContact());
			aRepo.save(admUpdate.get());
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

}