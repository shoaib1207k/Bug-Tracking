package com.cg.bugtracking.service.tests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.bugtracking.dao.AdminRepository;
import com.cg.bugtracking.service.AdminServiceImpl;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
	@Mock
	private AdminRepository aRepo;

	@InjectMocks
	private AdminServiceImpl aService;

}
