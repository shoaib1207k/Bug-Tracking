package com.cg.bugtracking.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeDTO {
	
	@NotNull
	@Min(value = 1, message="must be greater than zero")
	private long empId;
	@NotNull
	@Size(min = 3, message = "atleast 3 charachters required")
	private String empName;
	@NotNull
	@Email
	private String email;
	@NotNull
	@Size(min = 10)
	private String contact;
    
	private long projId;

	
	
	public long getProjId() {
		return projId;
	}


	public void setProjId(long projId) {
		this.projId = projId;
	}


	public EmployeeDTO() {
//		Hibernate creates an instance of entities using reflection it uses the Class.newInstance()

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
