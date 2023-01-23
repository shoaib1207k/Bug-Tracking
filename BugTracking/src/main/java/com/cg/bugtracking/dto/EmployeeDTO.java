package com.cg.bugtracking.dto;



public class EmployeeDTO {
	
	private long empId;
	private String empName;
	private String email;
	private String contact;
	private long projId;
//	private List<Project> projectList;
	
//	public EmployeeDTO(String empName, String email, String contact,long projId ) {
//		super();
////		this.empId = empId;
//		this.empName = empName;
//		this.email = email;
//		this.contact = contact;
////		this.projectList = projectList;
//		this.projId = projId; 
//	}
	
	
	public long getProjId() {
		return projId;
	}


	public void setProjId(long projId) {
		this.projId = projId;
	}


	public EmployeeDTO() {}

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
//	public List<Project> getProjectList() {
//		return projectList;
//	}
//	public void setProjectList(List<Project> projectList) {
//		this.projectList = projectList;
//	}

}
