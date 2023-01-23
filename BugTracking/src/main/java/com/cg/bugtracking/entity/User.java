package com.cg.bugtracking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_id")
	private long userId;

	@Column(name = "user_role")
	private String role;

	// if admin, returns true
	public boolean checkAdmin() {
		return (getRole().equals("admin"));
	}

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