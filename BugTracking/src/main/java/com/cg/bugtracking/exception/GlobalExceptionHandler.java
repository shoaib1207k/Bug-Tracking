package com.cg.bugtracking.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	@ExceptionHandler(value = { Exception.class })
	public ErrorInformation handleInternalServerError(Exception ex, HttpServletRequest req) {
		String msg = ex.getMessage();
		String url = req.getRequestURI();
		LocalDateTime ldt = LocalDateTime.now();
		return new ErrorInformation(url, msg, ldt);
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	@ExceptionHandler(value = { NoSuchUserFoundException.class })
	public ErrorInformation handleNotFound(Exception ex, HttpServletRequest req) {
		String msg = ex.getMessage();
		String url = req.getRequestURI();
		LocalDateTime ldt = LocalDateTime.now();
		return new ErrorInformation(url, msg, ldt);
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	@ExceptionHandler(value = { NoSuchEmployeeFoundException.class })
	public ErrorInformation empHandleNotFound(Exception ex, HttpServletRequest req) {
		String msg = ex.getMessage();
		String url = req.getRequestURI();
		LocalDateTime ldt = LocalDateTime.now();
		return new ErrorInformation(url, msg, ldt);
	}
	
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ErrorInformation handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest req) {
		String msg = "validation failed";
		FieldError error = ex.getFieldError();
		if (error != null) {
			msg = error.getDefaultMessage();
		}
		String url = req.getRequestURI();
		LocalDateTime ldt = LocalDateTime.now();
		return new ErrorInformation(url, msg, ldt);
	}
}
