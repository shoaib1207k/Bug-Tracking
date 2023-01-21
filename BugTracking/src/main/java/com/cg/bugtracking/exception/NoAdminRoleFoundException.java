package com.cg.bugtracking.exception;

@SuppressWarnings("serial")
public class NoAdminRoleFoundException extends Exception {

	public NoAdminRoleFoundException(String message) {
		super(message);
	}
}
