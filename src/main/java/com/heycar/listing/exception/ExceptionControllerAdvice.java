package com.heycar.listing.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.heycar.listing.dto.ErrorDetailsDto;

@ControllerAdvice
public class ExceptionControllerAdvice {

	
	/**
	 * This method helps to build the exception details if there is no Dealer
	 * available in system.
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(DealerNotFountException.class)
	public ResponseEntity<ErrorDetailsDto> dealerNotFountException(final Exception exception) {
		ErrorDetailsDto errorDetail = new ErrorDetailsDto(HttpStatus.NOT_FOUND.value(), "Dealer not available",
				exception.getMessage());
		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	}

	/**
	 * This method helps to Errors to controller if there is any Global Exception.
	 * 
	 * @param exception
	 * 
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetailsDto> handleException(final Exception exception) {
		ErrorDetailsDto errorDetail = new ErrorDetailsDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal Server error", exception.getMessage());
		return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
