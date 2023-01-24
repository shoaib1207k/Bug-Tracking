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
import com.cg.bugtracking.dto.UserDTO;
import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.entity.User;
import com.cg.bugtracking.exception.IdAlreadyExistsException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.exception.NotAdminException;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);
	private static final String NO_USER_FOUND = "User ID not found.";
	private static final String ID_EXISTS = "ID already exists.";
	private static final String NOT_ADMIN = "You are not an Admin!";

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private AdminRepository aRepo;

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
	public List<UserDTO> findAllUsers(long adminId) throws NotAdminException {
		Optional<Admin> findAdmin = aRepo.findById(adminId);
		if (findAdmin.isPresent()) {
			LOG.info("Returning all users");
			return uRepo.findAll().stream().map(usr -> modelMapper.map(usr, UserDTO.class))
					.collect(Collectors.toList());
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}

	@Override
	public UserDTO findById(long id, long adminId) throws NoSuchUserFoundException, NotAdminException {
		Optional<Admin> findAdmin = aRepo.findById(adminId);
		Optional<User> usr = uRepo.findById(id);
		if (findAdmin.isPresent()) {
			if (usr.isPresent()) {
				LOG.info("Returning user using id");
				return modelMapper.map(usr.get(), UserDTO.class);
			} else {
				LOG.error(NO_USER_FOUND);
				throw new NoSuchUserFoundException(NO_USER_FOUND);
			}
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}

	@Override
	public UserDTO updateUser(long id, UserDTO userDto, long adminId)
			throws NoSuchUserFoundException, NotAdminException {
		Optional<User> usrUpdate = uRepo.findById(id);
		User user = modelMapper.map(userDto, User.class);
		Optional<Admin> findAdmin = aRepo.findById(adminId);
		if (findAdmin.isPresent()) {
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
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}

	@Override
	public UserDTO deleteUser(long id, long adminId) throws NoSuchUserFoundException, NotAdminException {
		Optional<User> usrDel = uRepo.findById(id);
		Optional<Admin> findAdmin = aRepo.findById(adminId);
		if (findAdmin.isPresent()) {
			if (usrDel.isPresent()) {
				LOG.info("Deleting...");
				uRepo.delete(usrDel.get());
				LOG.info("Deleted.");
			} else {
				LOG.error(NO_USER_FOUND);
				throw new NoSuchUserFoundException(NO_USER_FOUND);
			}
			return modelMapper.map(usrDel.get(), UserDTO.class);
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}

}
