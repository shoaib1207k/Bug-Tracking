package com.cg.bugtracking.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeDTO {

	@NotBlank(message = "must not be blank")
	@Min(value = 1, message = "must be greater than zero")
	private long empId;
	
	@NotNull
	@Size(min = 3, message = "atleast 3 charachters required")
	private String empName;
	
	@NotNull
	@Email(message = "enter valid email")
	private String email;
	
	@NotNull
	@Size(min = 10, message = "must be greater than 10")
	private String contact;

	private ProjectDTO projectDTO;

	public ProjectDTO getProject() {
		return projectDTO;
	}

	public void setProject(ProjectDTO project) {
		this.projectDTO = project;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}