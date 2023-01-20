package com.cg.bugtracking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "admins")
public class Admin extends User {

	@Id
	@Column(name = "admin_id", updatable = false)
	private long adminId;

	@Column(name = "admin_name", nullable = false)
	@NotBlank(message = "Name is required")
	@Size(max = 25, min = 5, message = "atleast 5 characters")
	private String adminName;

	@Column(name = "admin_contact", nullable = false, unique = true)
	@NotBlank(message = "Contact is required")
	@Size(max = 15, min = 10, message = "atleast 10 numbers")
	private String adminContact;

	public Admin() {
		super();
	}

	public Admin(String adminName, String adminContact) {
		super();
		this.adminId = super.getUserId();
		this.adminName = adminName;
		this.adminContact = adminContact;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminContact() {
		return adminContact;
	}

	public void setAdminContact(String adminContact) {
		this.adminContact = adminContact;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminContact=" + adminContact + "]";
	}

}
