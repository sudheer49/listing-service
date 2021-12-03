package com.heycar.listing.exception;

public class DealerNotFountException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DealerNotFountException(String message) {
		super(message);
	}

	public DealerNotFountException() {
		super();
	}

}
