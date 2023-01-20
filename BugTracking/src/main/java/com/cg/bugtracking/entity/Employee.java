package com.cg.bugtracking.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Employee {
	
	@Id
	private long empId;
	private String empName;
	private String email;
	private String contact;
	
	@OneToMany
	private List<Project> projectList;
	
	public Employee() {}
	
	public Employee(long empId, String empName, String email, String contact, List<Project> projectList) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.email = email;
		this.contact = contact;
		this.projectList = projectList;
	}
	
	
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
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
	public List<Project> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", email=" + email + ", contact=" + contact
				+ ", projectList=" + projectList + "]";
	}
	
}
