package com.cg.bugtracking.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;


@Table(name="employees")
@Scope("prototype")
@Entity
public class Employee {
	
	@Id
	@Column(updatable = false)
	private long empId;
	private String empName;
	private String email;
	private String contact;
	
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;
	
	public Employee() {}


	public Employee(long empId, String empName, String email, String contact, Project project) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.email = email;
		this.contact = contact;
		this.project = project;
	}


	public long getEmpId() {
		return empId;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
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