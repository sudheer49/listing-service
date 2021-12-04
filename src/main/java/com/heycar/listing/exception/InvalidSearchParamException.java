package com.heycar.listing.exception;

public class InvalidSearchParamException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidSearchParamException(String message) {
		super(message);
	}

	public InvalidSearchParamException() {
		super();
	}

}
