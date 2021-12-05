package com.heycar.listing.exception;

/**
 * This class is custom exception class which will be thrown if the there is
 * error while converting CSV to ListingDtos
 * 
 * @author Satya Kolipaka
 *
 */
public class CSVConverterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CSVConverterException(String message) {
		super(message);
	}

	public CSVConverterException() {
		super();
	}

}