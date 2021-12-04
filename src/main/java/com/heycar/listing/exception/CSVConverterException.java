package com.heycar.listing.exception;

public class CSVConverterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CSVConverterException(String message) {
		super(message);
	}

	public CSVConverterException() {
		super();
	}

}