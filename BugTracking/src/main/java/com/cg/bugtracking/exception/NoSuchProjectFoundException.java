package com.cg.bugtracking.exception;

@SuppressWarnings("serial")
public class NoSuchProjectFoundException extends Exception {

	public NoSuchProjectFoundException(String message) {
		super(message);
	}
}
