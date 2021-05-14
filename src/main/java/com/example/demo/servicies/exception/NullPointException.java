package com.example.demo.servicies.exception;

public class NullPointException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NullPointException(String msg) {
		super(msg);
	}
}