package com.cg.bugtracking.service;

import java.util.List;
import java.util.Optional;

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
	public User createUser(User user) {
		return uRepo.save(user);
	}

	@Override
	public List<User> findAllUsers() {
		return uRepo.findAll();
	}

	@Override
	public boolean deleteUser(long id) {
		uRepo.deleteById(id);
		Optional<User> find = uRepo.findById(id);
		return !(find.isPresent());
	}

}
