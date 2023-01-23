package com.cg.bugtracking.dto;



public class EmployeeDTO {
	
	private long empId;
	private String empName;
	private String email;
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
