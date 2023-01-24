package com.cg.bugtracking.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.cg.bugtracking.dao.AdminRepository;
import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.dto.UserDTO;

class TestAdminServiceImpl {

	@Mock
	private AdminRepository adminRepository;

	@InjectMocks
	private AdminServiceImpl adminServiceImpl;

	private UserDTO userDto;
	private AdminDTO adminDto;

	@BeforeEach
	public void init() {

	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

}
