package com.lcwd.user.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	
	public ResourceNotFoundException()
	{
		super("Resource not Found On Server..!!");
	}
	public ResourceNotFoundException(String message)
	{
		super(message);
	}
}
