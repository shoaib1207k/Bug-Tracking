package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.UserDTO;
import com.cg.bugtracking.exception.IdAlreadyExistsException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.exception.NotAdminException;

public interface UserService {

	UserDTO createUser(UserDTO userDto) throws IdAlreadyExistsException;

	UserDTO findById(long id, long adminId) throws NoSuchUserFoundException, NotAdminException;

	List<UserDTO> findAllUsers(long adminId) throws NotAdminException;

	UserDTO updateUser(long id, UserDTO userDto, long adminId) throws NoSuchUserFoundException, NotAdminException;

	UserDTO deleteUser(long id, long adminId) throws NoSuchUserFoundException, NotAdminException;
}
