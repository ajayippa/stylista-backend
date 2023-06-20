package com.stoned.app.exception;

public class MobileNumberException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public MobileNumberException()
	{
		super();
	}
	public MobileNumberException(String message)
	{
		super(message);
				
	}
}
