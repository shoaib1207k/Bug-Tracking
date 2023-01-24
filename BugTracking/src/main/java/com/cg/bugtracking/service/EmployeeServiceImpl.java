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

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final String NO_USER_FOUND = "User ID not found.";	
	private static final String ADMIN_ROLE_REQUIRED = "Admin role is required.";
	
	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProjectService prjService;
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private AdminRepository adminRepo;

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO empDTO, long adminID) throws NoAdminRoleFoundException, NoSuchUserFoundException {
		
	  Optional<User> user = uRepo.findById(empDTO.getEmpId());
	  
	  Optional<Admin> findAdmin = adminRepo.findById(adminID);
	  
	  if (findAdmin.isPresent()) {
		  if (user.isPresent()) {
            Employee emp = modelMapper.map(empDTO, Employee.class);
            empRepo.save(emp);
            return empDTO;
        } else {
        	throw new NoSuchUserFoundException(NO_USER_FOUND);
        } 
     }else {
        throw new NoAdminRoleFoundException(ADMIN_ROLE_REQUIRED);
    }
        
	}

	@Override
	public EmployeeDTO getEmployeeById(long empId) throws NoSuchEmployeeFoundException {
		Optional<Employee> emp = empRepo.findById(empId);
		if (emp.isPresent())
			return modelMapper.map(emp.get(), EmployeeDTO.class);
		throw new NoSuchEmployeeFoundException("No Such employee found");
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		return empRepo.findAll().stream().map(emp -> modelMapper.map(emp, EmployeeDTO.class))
				.collect(Collectors.toList());

	}

	@Override
	public EmployeeDTO updateEmployee(long id, EmployeeDTO empDTO)
			throws NoSuchEmployeeFoundException, NoSuchProjectFoundException {
		Optional<Employee> empToUpdate = empRepo.findById(id);
		Employee emp = modelMapper.map(empDTO, Employee.class);

		if (empToUpdate.isPresent()) {

			empToUpdate.get().setEmpName(emp.getEmpName());
			empToUpdate.get().setEmail(emp.getEmail());
			empToUpdate.get().setContact(emp.getContact());
//			if (prjService.getProjectById(emp.getProjId()) != null) {
				empToUpdate.get().setProjList(emp.getProjList());
//			}
			empRepo.save(empToUpdate.get());
			return empDTO;
		} else {
			throw new NoSuchEmployeeFoundException("No employee with this id");
		}
	}

	@Override
	public EmployeeDTO deleteEmployee(long id) throws NoSuchEmployeeFoundException {
		Optional<Employee> empToDel = empRepo.findById(id);
		if (empToDel.isPresent())
			empRepo.delete(empToDel.get());
		else
			throw new NoSuchEmployeeFoundException("No employee with this id");
		return modelMapper.map(empToDel.get(), EmployeeDTO.class);
	}

}