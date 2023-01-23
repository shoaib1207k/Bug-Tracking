package com.cg.bugtracking.service;

import java.util.List;
import com.cg.bugtracking.dto.UserDTO;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;

public interface UserService {

	UserDTO createUser(UserDTO userDto) throws NoSuchUserFoundException, NoAdminRoleFoundException;

	UserDTO findById(long id) throws NoSuchUserFoundException;

	List<UserDTO> findAllUsers();

	UserDTO updateUser(long id, UserDTO userDto) throws NoSuchUserFoundException;

	UserDTO deleteUser(long id) throws NoSuchUserFoundException;
}
