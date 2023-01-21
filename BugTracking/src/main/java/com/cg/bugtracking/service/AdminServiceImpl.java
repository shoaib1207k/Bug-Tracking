package com.cg.bugtracking.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bugtracking.dao.AdminRepository;
import com.cg.bugtracking.dao.UserRepository;
import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.entity.User;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository aRepo;

	@Autowired
	private UserRepository uRepo;

	@Override
	@Transactional
	public Admin createAdmin(Admin admin) throws NoSuchUserFoundException, NoAdminRoleFoundException {
		Optional<User> find = uRepo.findById(admin.getAdminId());
		if (find.isPresent()) {
			if (find.get().checkAdmin()) {
				return aRepo.save(admin);
			} else {
				throw new NoAdminRoleFoundException("Admin role is required.");
			}
		} else {
			throw new NoSuchUserFoundException("User ID not found.");
		}
	}

	@Override
	public List<Admin> findAllAdmins() {
		return aRepo.findAll();
	}

	@Override
	public Admin findAdminById(long id) throws NoSuchAdminFoundException {
		Optional<Admin> crs = aRepo.findById(id);
		if (crs.isPresent())
			return crs.get();

		throw new NoSuchAdminFoundException("Admin ID not found.");
	}

	@Override
	@Transactional
	public Admin updateAdmin(long id, Admin admin) throws NoSuchAdminFoundException {
		Admin find = findAdminById(id);
		find.setAdminId(admin.getAdminId());
		find.setAdminName(admin.getAdminName());
		find.setAdminContact(admin.getAdminContact());
		return aRepo.save(find);
	}

	@Override
	public boolean deleteAdmin(long id) {
		aRepo.deleteById(id);
		Optional<Admin> find = aRepo.findById(id);
		return !(find.isPresent());
	}

}