package com.chamith.app.ws.exceptions;

public class UserServiceException extends RuntimeException {


	private static final long serialVersionUID = 4810634622855877388L;

	public UserServiceException(String message)
	{
		super(message);
	}
}
