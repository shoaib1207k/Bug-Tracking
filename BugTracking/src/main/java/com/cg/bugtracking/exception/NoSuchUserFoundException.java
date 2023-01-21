package com.cg.bugtracking.exception;

@SuppressWarnings("serial")
public class NoSuchUserFoundException extends Exception {

	public NoSuchUserFoundException(String message) {
		super(message);
	}
}
