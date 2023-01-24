package com.cg.bugtracking.exception;

@SuppressWarnings("serial")
public class NotAdminException extends Exception {

	public NotAdminException(String message) {
		super(message);
	}
}
