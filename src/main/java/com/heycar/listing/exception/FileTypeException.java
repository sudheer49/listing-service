package com.heycar.listing.exception;

public class FileTypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileTypeException(String message) {
		super(message);
	}

	public FileTypeException() {
		super();
	}

}
