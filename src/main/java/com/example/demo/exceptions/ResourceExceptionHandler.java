package com.example.demo.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.servicies.exception.DatabaseConstraintViolationException;
import com.example.demo.servicies.exception.ResourceNotFoundException;
import com.example.demo.servicies.exception.ValidationErrorException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e,HttpServletRequest request){
		String error = "resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseConstraintViolationException.class)
	public ResponseEntity<StandardError> databaseException(DatabaseConstraintViolationException e,HttpServletRequest request){
		String error = "Database constraint violation exception";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ValidationErrorException.class)
	public ResponseEntity<StandardError> validationErrorException(ValidationErrorException e,HttpServletRequest request){
		String error = "Check violation exception";
		HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
}
