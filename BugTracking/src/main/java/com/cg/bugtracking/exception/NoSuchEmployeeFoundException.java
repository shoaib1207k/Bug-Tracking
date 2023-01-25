package com.cg.bugtracking.exception;

@SuppressWarnings("serial")
public class NoSuchEmployeeFoundException extends Exception{

	public NoSuchEmployeeFoundException(String message) {
		super(message);
	}
	
}
