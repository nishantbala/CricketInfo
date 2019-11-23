package com.stackroute.userservice.exception;

public class ResponseData {
	private String status;
	private String message;
	
	public ResponseData(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

}