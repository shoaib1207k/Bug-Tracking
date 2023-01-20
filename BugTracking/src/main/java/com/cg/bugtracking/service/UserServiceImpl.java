package com.cg.bugtracking.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bugtracking.dao.UserRepository;
import com.cg.bugtracking.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository uRepo;

	@Override
	@Transactional
	public void createRoles() {
		User user = new User(0, "user");
		User admin = new User(1, "admin");
		uRepo.saveAll(List.of(user, admin));
	}

	@Override
	@Transactional
	public User createUser(User user) {
		User roleUser = uRepo.findByName(user.getRole());
		user.setRole(roleUser.getRole());
		return uRepo.save(user);
	}

	@Override
	public User findByName(String name) {
		return uRepo.findByName(name);
	}
}
