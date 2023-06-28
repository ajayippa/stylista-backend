package com.stoned.app.exception;

public class StoryAlreadyExistException extends Exception{
	
private static final long serialVersionUID = 1L;
	
	public StoryAlreadyExistException()
	{
		super();
	}
	public StoryAlreadyExistException(String message)
	{
		super(message);
				
	}

}
