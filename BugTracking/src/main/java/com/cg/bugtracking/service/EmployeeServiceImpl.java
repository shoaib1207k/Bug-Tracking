package com.cg.bugtracking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bugtracking.dao.AdminRepository;
import com.cg.bugtracking.dao.EmployeeRepository;
import com.cg.bugtracking.dao.UserRepository;
import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.entity.Employee;
import com.cg.bugtracking.entity.User;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchEmployeeFoundException;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.exception.NotAdminException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final String NO_USER_FOUND = "User ID not found.";

	private static final String ADMIN_ROLE_REQUIRED = "Admin role is required.";

	private static final String NOT_ADMIN = "You are not admin.";
	
	private static final String NO_EMPLOYEE_FOUND = "No employee found with this ID.";
	
	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private AdminRepository adminRepo;

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO empDTO, long adminId)
			throws NoAdminRoleFoundException, NoSuchUserFoundException, NotAdminException {
		Optional<User> findUser = uRepo.findById(empDTO.getEmpId());
		Optional<Admin> findAdmin = adminRepo.findById(adminId);
		if (findAdmin.isPresent()) {
			if (findUser.isPresent()) {
				if (findUser.get().checkAdmin() || findUser.get().checkEmployee()) {
					Employee emp = modelMapper.map(empDTO, Employee.class);
					empRepo.save(emp);
					return empDTO;
				} else {
					throw new NoAdminRoleFoundException(ADMIN_ROLE_REQUIRED);
				}
			} else {
				throw new NoSuchUserFoundException(NO_USER_FOUND);
			}
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}

	@Override
	public EmployeeDTO getEmployeeById(long empId, long adminId)
			throws NoSuchEmployeeFoundException, NotAdminException {
		Optional<Admin> findAdmin = adminRepo.findById(adminId);
		if (findAdmin.isPresent()) {
			Optional<Employee> emp = empRepo.findById(empId);
			if (emp.isPresent())
				return modelMapper.map(emp.get(), EmployeeDTO.class);
			throw new NoSuchEmployeeFoundException(NO_EMPLOYEE_FOUND);
		} else
			throw new NotAdminException(NOT_ADMIN);

	}

	@Override
	public List<EmployeeDTO> getAllEmployees(long adminId) throws NotAdminException {
		Optional<Admin> findAdmin = adminRepo.findById(adminId);
		if (findAdmin.isPresent()) {
			return empRepo.findAll().stream().map(emp -> modelMapper.map(emp, EmployeeDTO.class))
					.collect(Collectors.toList());
		} else {
			throw new NotAdminException(NOT_ADMIN);

		}
	}

	@Override
	public EmployeeDTO updateEmployee(long id, EmployeeDTO empDTO, long adminId)
			throws NoSuchEmployeeFoundException,NotAdminException {
		
		Optional<Admin> findAdmin = adminRepo.findById(adminId);
		if(findAdmin.isPresent()) {
			Optional<Employee> empToUpdate = empRepo.findById(id);
			Employee emp = modelMapper.map(empDTO, Employee.class);
			if (empToUpdate.isPresent()) {
	
				empToUpdate.get().setEmpName(emp.getEmpName());
				empToUpdate.get().setEmail(emp.getEmail());
				empToUpdate.get().setContact(emp.getContact());
				empToUpdate.get().setProject(emp.getProject());
				
				empRepo.save(empToUpdate.get());
				return empDTO;
			} else {
				throw new NoSuchEmployeeFoundException(NO_EMPLOYEE_FOUND);
			}
		}else{
			throw new NotAdminException(NOT_ADMIN);
		}
	}

	@Override
	public EmployeeDTO deleteEmployee(long empId, long adminId)
			throws NoSuchEmployeeFoundException, NotAdminException {
		Optional<Admin> findAdmin = adminRepo.findById(adminId);
		if (findAdmin.isPresent()) {
			Optional<Employee> empToDel = empRepo.findById(empId);
			if (empToDel.isPresent())
				empRepo.delete(empToDel.get());
			else
				throw new NoSuchEmployeeFoundException(NO_EMPLOYEE_FOUND);
			return modelMapper.map(empToDel.get(), EmployeeDTO.class);
		} else
			throw new NotAdminException(NOT_ADMIN);
	}

	@Override
	public List<EmployeeDTO> getEmployeeByProjectId(long adminId, long projId) throws NotAdminException {
		Optional<Admin> findAdmin = adminRepo.findById(adminId);
		if (findAdmin.isPresent()) {
			return empRepo.getEmployeeByProjectId(projId).stream().map(emp -> modelMapper.map(emp, EmployeeDTO.class))
					.collect(Collectors.toList());
		} else
			throw new NotAdminException(NOT_ADMIN);

	}

}