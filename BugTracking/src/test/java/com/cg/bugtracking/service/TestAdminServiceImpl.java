package com.cg.bugtracking.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.bugtracking.dao.AdminRepository;
import com.cg.bugtracking.dao.UserRepository;
import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.dto.UserDTO;

class TestAdminServiceImpl {

	@Mock
	private AdminRepository adminRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private AdminServiceImpl adminServiceImpl;

	private UserDTO userDto;
	private AdminDTO adminDto;

	@BeforeEach
	public void init() {
		// user details
		userDto = new UserDTO();
		userDto.setUserId(1);
		userDto.setRole("admin");
		// admin details
		adminDto = new AdminDTO();
		adminDto.setAdminId(1);
		adminDto.setAdminName("Rahul");
		adminDto.setAdminContact("1234567890");
		adminServiceImpl = new AdminServiceImpl();
		MockitoAnnotations.openMocks(this);
	}

//	@Test
//	void testAddAdmin() throws NoSuchUserFoundException, NoAdminRoleFoundException, IdAlreadyExistsException {
//
//	}

}
