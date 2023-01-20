package com.cg.bugtracking.exception;

import java.time.LocalDateTime;

public class ErrorInformation {

	private String url;
	private String message;
	private LocalDateTime localDateTime;

	public ErrorInformation(String url, String message, LocalDateTime localDateTime) {
		super();
		this.url = url;
		this.message = message;
		this.localDateTime = localDateTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

}