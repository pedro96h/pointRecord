package com.example.demo.servicies.exception;

public class TotalWorkloadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TotalWorkloadException(String msg) {
		super("Invalid date input , " + msg);
	}
}
