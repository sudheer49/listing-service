package com.heycar.listing.exception;

/**
 * This class is custom exception class which will be thrown if the search
 * Params are invalid
 * 
 * @author Satya Kolipaka
 *
 */
public class InvalidSearchParamException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidSearchParamException(String message) {
		super(message);
	}

	public InvalidSearchParamException() {
		super();
	}

}
