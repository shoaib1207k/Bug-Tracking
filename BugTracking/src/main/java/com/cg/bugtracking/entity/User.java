package com.cg.bugtracking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@Column(name = "user_role", nullable = false)
	@NotBlank(message = "Role is required")
	private String role;	
	
	public User() {
	}

	public User(long userId, String role) {
		super();
		this.userId = userId;
		this.role = role;
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

	@Override
	public String toString() {
		return "User [userId=" + userId + ", role=" + role + "]";
	}

}
