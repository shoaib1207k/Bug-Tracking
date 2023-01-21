package com.cg.bugtracking.exception;

@SuppressWarnings("serial")
public class NoSuchAdminFoundException extends Exception {

	public NoSuchAdminFoundException(String message) {
		super(message);
	}
}
