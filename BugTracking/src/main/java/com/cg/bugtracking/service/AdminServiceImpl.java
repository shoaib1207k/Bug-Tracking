package com.cg.bugtracking.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.bugtracking.dao.AdminRepository;
import com.cg.bugtracking.dao.UserRepository;
import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.dto.UserDTO;
import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.entity.User;

public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepository uRepo;
	@Autowired
	private AdminRepository aRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public UserDTO createUser(User user) {
		return modelMapper.map(uRepo.save(user), UserDTO.class);
	}

	@Override
	@Transactional
	public AdminDTO createAdmin(Admin admin) {
		return modelMapper.map(aRepo.save(admin), AdminDTO.class);
	}

}
