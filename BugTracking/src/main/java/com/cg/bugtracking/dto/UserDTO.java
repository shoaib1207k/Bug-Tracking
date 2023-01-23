package com.cg.bugtracking.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTO {

	@Min(value = 1, message = "Should be greater than 1 in user ID")
	private long userId;
	@NotBlank(message = "Role is required")
	@Size(max = 15, min = 5, message = "atleast 5 characters in role")
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
