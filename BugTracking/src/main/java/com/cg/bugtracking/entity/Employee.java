package com.cg.bugtracking.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="employees")
public class Employee {
	
	@Id
	@Column(updatable = false)
	private long empId;
	private String empName;
	private String email;
	private String contact;
	private long projId;
	
	public Employee() {}
	
	public Employee(long empId, String empName, String email, String contact, long projId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.email = email;
		this.contact = contact;
		this.projId	= projId;
	}
	
	
	public long getProjId() {
		return projId;
	}

	public void setProjId(long projId) {
		this.projId = projId;
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


	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", email=" + email + ", contact=" + contact
				+ ", projId=" + projId + "]";
	}
	
}
