package com.stoned.app.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class MainExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String,String> handleInvalidException(
										MethodArgumentNotValidException reg)
	{
		Map<String,String> errorMap= new HashMap<>();
		reg.getBindingResult().getFieldErrors().forEach(error -> {
				errorMap.put("errorMessage",error.getDefaultMessage());
		});
		return errorMap;
	}
	
	
	@ExceptionHandler(PasswordMisMatchException.class)
	public String handleLoginException(PasswordMisMatchException id)
	{
		
		return id.getMessage();
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public String userExistException(UserAlreadyExistException ua)
	{
		
		return ua.getMessage();
	}
	
	@ExceptionHandler(MobileNumberException.class)
	public String mobileNumberException(MobileNumberException mn)
	{
		
		return mn.getMessage();
	}

}
