package com.cg.bugtracking.serviceimpl;

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
import com.cg.bugtracking.exception.IdAlreadyExistsException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final String NO_USER_FOUND = "User ID not found.";

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public UserDTO createUser(UserDTO userDto) throws IdAlreadyExistsException {
		Optional<User> findUser = uRepo.findById(userDto.getUserId());
		if (findUser.isPresent()) {
			throw new IdAlreadyExistsException("ID already exists in the database");
		} else {
			User user = modelMapper.map(userDto, User.class);
			uRepo.save(user);
			return userDto;
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
