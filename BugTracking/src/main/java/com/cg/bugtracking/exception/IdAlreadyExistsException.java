package com.cg.bugtracking.exception;

@SuppressWarnings("serial")
public class IdAlreadyExistsException extends Exception {

	public IdAlreadyExistsException(String message) {
		super(message);
	}
}
