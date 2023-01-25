package com.cg.bugtracking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.context.annotation.Scope;

@Entity
@Table(name = "admins")
@Scope("prototype")
public class Admin {

	@Id
	@Column(name = "admin_id")
	private long adminId;

	@Column(name = "admin_name")
	private String adminName;

	@Column(name = "admin_contact")
	private String adminContact;

	public Admin() {
	}

	public Admin(long adminId, String adminName, String adminContact) {
		this.adminId = adminId;
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