package com.stoned.app.exception;

public class UserAlreadyExistException extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistException()
	{
		super();
	}
	public UserAlreadyExistException(String message)
	{
		super(message);
				
	}

}
