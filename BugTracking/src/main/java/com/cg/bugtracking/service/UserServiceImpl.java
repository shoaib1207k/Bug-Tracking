package com.cg.bugtracking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.bugtracking.dao.UserRepository;
import com.cg.bugtracking.dto.UserDTO;
import com.cg.bugtracking.entity.User;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;

@Service
public class UserServiceImpl implements UserService {

	private static final String NO_USER_FOUND = "User ID not found.";

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public UserDTO createUser(UserDTO userDto) throws NoSuchUserFoundException, NoAdminRoleFoundException {
		Optional<User> find = uRepo.findById(userDto.getUserId());
		if (find.isPresent()) {
			if (find.get().checkAdmin()) {
				User user = modelMapper.map(userDto, User.class);
				uRepo.save(user);
				return userDto;
			} else {
				throw new NoAdminRoleFoundException("Admin role is required.");
			}
		} else {
			throw new NoSuchUserFoundException(NO_USER_FOUND);
		}
	}

	@Override
	public List<UserDTO> findAllUsers() {
		return uRepo.findAll().stream().map(usr -> modelMapper.map(usr, UserDTO.class)).collect(Collectors.toList());
	}

	@Override
	public UserDTO findById(long id) throws NoSuchUserFoundException {
		Optional<User> usr = uRepo.findById(id);
		if (usr.isPresent())
			return modelMapper.map(usr.get(), UserDTO.class);
		throw new NoSuchUserFoundException(NO_USER_FOUND);
	}

	@Override
	@Transactional
	public UserDTO updateUser(long id, UserDTO userDto) throws NoSuchUserFoundException {
		Optional<User> usrUpdate = uRepo.findById(id);
		User user = modelMapper.map(userDto, User.class);
		if (usrUpdate.isPresent()) {
			usrUpdate.get().setRole(user.getRole());
			uRepo.save(usrUpdate.get());
			return userDto;
		} else {
			throw new NoSuchUserFoundException(NO_USER_FOUND);
		}
	}

	@Override
	@Transactional
	public UserDTO deleteUser(long id) throws NoSuchUserFoundException {
		Optional<User> usrDel = uRepo.findById(id);
		if (usrDel.isPresent())
			uRepo.delete(usrDel.get());
		else
			throw new NoSuchUserFoundException(NO_USER_FOUND);
		return modelMapper.map(usrDel.get(), UserDTO.class);
	}

}
