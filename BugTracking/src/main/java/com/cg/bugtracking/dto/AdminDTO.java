package com.cg.bugtracking.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AdminDTO {

	private long adminId;

	@NotBlank(message = "Name is required")
	@Size(max = 25, min = 5, message = "atleast 5 characters in name")
	private String adminName;

	@NotBlank(message = "Contact is required")
	@Size(max = 15, min = 10, message = "atleast 10 numbers in contact")
	private String adminContact;

	public AdminDTO() {
		// no-args constructor
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

}
