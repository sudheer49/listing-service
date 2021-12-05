package com.heycar.listing.exception;

/**
 * This class is custom exception class which will be thrown if the there is
 * No Dealer found on given Id
 * 
 * @author Satya Kolipaka
 *
 */
public class DealerNotFountException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DealerNotFountException(String message) {
		super(message);
	}

	public DealerNotFountException() {
		super();
	}

}
