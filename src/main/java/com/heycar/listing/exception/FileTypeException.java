package com.heycar.listing.exception;

/**
 * This class is custom exception class which will be thrown if the file type is
 * not CSV
 * 
 * @author Satya Kolipaka
 *
 */
public class FileTypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileTypeException(String message) {
		super(message);
	}

	public FileTypeException() {
		super();
	}

}
