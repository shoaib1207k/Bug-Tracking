package com.cg.bugtracking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bugtracking.dao.UserRepository;
import com.cg.bugtracking.dto.UserDTO;
import com.cg.bugtracking.entity.User;
import com.cg.bugtracking.exception.IdAlreadyExistsException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);
	private static final String NO_USER_FOUND = "User ID not found.";
	private static final String ID_EXISTS = "ID already exists.";

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private ModelMapper modelMapper;

	// create (user, bug)

	@Override
	public UserDTO createUser(UserDTO userDto) throws IdAlreadyExistsException {
		Optional<User> findUser = uRepo.findById(userDto.getUserId());
		if (findUser.isPresent()) {
			LOG.error(ID_EXISTS);
			throw new IdAlreadyExistsException(ID_EXISTS);
		} else {
			User user = modelMapper.map(userDto, User.class);
			LOG.info("Saving user");
			uRepo.save(user);
			LOG.info("Saved. Returning user");
			return userDto;
		}
	}

	@Override
	public List<UserDTO> findAllUsers() {
		LOG.info("Returning all users");
		return uRepo.findAll().stream().map(usr -> modelMapper.map(usr, UserDTO.class)).collect(Collectors.toList());
	}

	@Override
	public UserDTO findById(long id) throws NoSuchUserFoundException {
		Optional<User> usr = uRepo.findById(id);
		if (usr.isPresent()) {
			LOG.info("Returning user using id");
			return modelMapper.map(usr.get(), UserDTO.class);
		} else {
			LOG.error(NO_USER_FOUND);
			throw new NoSuchUserFoundException(NO_USER_FOUND);
		}
	}

	@Override
	public UserDTO updateUser(long id, UserDTO userDto) throws NoSuchUserFoundException {
		Optional<User> usrUpdate = uRepo.findById(id);
		User user = modelMapper.map(userDto, User.class);
		if (usrUpdate.isPresent()) {
			LOG.info("User present. Updating...");
			usrUpdate.get().setRole(user.getRole());
			LOG.info("Saving...");
			uRepo.save(usrUpdate.get());
			LOG.info("Saved. Returning user");
			return userDto;
		} else {
			LOG.error(NO_USER_FOUND);
			throw new NoSuchUserFoundException(NO_USER_FOUND);
		}
	}

	@Override
	public UserDTO deleteUser(long id) throws NoSuchUserFoundException {
		Optional<User> usrDel = uRepo.findById(id);
		if (usrDel.isPresent()) {
			LOG.info("Deleting...");
			uRepo.delete(usrDel.get());
			LOG.info("Deleted.");
		} else {
			LOG.error(NO_USER_FOUND);
			throw new NoSuchUserFoundException(NO_USER_FOUND);
		}
		return modelMapper.map(usrDel.get(), UserDTO.class);
	}

}
