package com.cg.bugtracking;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bugtracking.controller.AdminController;
import com.cg.bugtracking.controller.BugController;
import com.cg.bugtracking.controller.EmployeeController;
import com.cg.bugtracking.controller.ProjectController;
import com.cg.bugtracking.controller.UserController;

@SpringBootTest
class BugTrackingApplicationTests {

	@Autowired
	private AdminController adminController;
	
	@Autowired
	private BugController bugController;
	
	@Autowired
	private EmployeeController employeeController;
	
	@Autowired
	private ProjectController projectController;
	
	@Autowired
	private UserController userController;

	
	@Test
	void contextLoads() {
		
		assertThat(userController).isNotNull();
		assertThat(projectController).isNotNull();
		assertThat(employeeController).isNotNull();
		assertThat(bugController).isNotNull();
		assertThat(adminController).isNotNull();
	}

}
