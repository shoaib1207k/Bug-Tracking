package com.cg.bugtracking.exception;


@SuppressWarnings("serial")
public class NoSuchBugFoundException extends Exception{

	public NoSuchBugFoundException(String message) {
		super(message);
		
	}
	
}
