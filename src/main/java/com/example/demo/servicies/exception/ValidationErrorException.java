package com.example.demo.servicies.exception;

public class ValidationErrorException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ValidationErrorException (String error) {
		super(error);
	}
}
