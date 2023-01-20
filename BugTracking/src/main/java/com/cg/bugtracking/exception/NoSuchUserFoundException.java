package com.cg.bugtracking.exception;

@SuppressWarnings("serial")
public class NoSuchUserFoundException extends Exception {

	NoSuchUserFoundException(String message) {
		super(message);
	}
}
