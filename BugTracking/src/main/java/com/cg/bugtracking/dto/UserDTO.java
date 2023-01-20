package com.cg.bugtracking.dto;

public class UserDTO {

	private long userId;
	private String role;

	public UserDTO() {
		// no args constructor
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
