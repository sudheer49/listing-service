package com.heycar.listing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.heycar.listing.dto.ErrorDetailsDto;

/**
 * Centralized exception handler class for ListingController
 * 
 * @author Satya Kolipaka
 *
 */
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
	 * This method helps to build the exception details if the upload file type is
	 * not CSV
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(FileTypeException.class)
	public ResponseEntity<ErrorDetailsDto> fileTypeException(final FileTypeException exception) {
		ErrorDetailsDto errorDetail = new ErrorDetailsDto(HttpStatus.BAD_REQUEST.value(), "Bad file type ",
				"Please upload only CSV file type");
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method helps to build the exception details if wrong format of CSV file
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(CSVConverterException.class)
	public ResponseEntity<ErrorDetailsDto> csvConverterException(final CSVConverterException exception) {
		ErrorDetailsDto errorDetail = new ErrorDetailsDto(HttpStatus.BAD_REQUEST.value(), "Bad file format ",
				"Please upload correct CSV fromat");
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method helps to build the exception details if any search params are
	 * wrong
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(InvalidSearchParamException.class)
	public ResponseEntity<ErrorDetailsDto> invalidSearchParamException(final InvalidSearchParamException exception) {
		ErrorDetailsDto errorDetail = new ErrorDetailsDto(HttpStatus.BAD_REQUEST.value(),
				"Bad Request parameters for search", exception.getMessage());
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
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
