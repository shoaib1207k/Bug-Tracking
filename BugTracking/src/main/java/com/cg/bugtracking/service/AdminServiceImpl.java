package com.cg.bugtracking.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bugtracking.dao.AdminRepository;
import com.cg.bugtracking.entity.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository aRepo;

	@Override
	@Transactional
	public Admin createAdmin(Admin admin) {
		return aRepo.save(admin);
	}

	@Override
	public List<Admin> findAllAdmins() {
		return aRepo.findAll();
	}

}
