package com.heycar.listing.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.heycar.listing.dto.ErrorDetailsDto;
import com.heycar.listing.dto.ListingDto;
import com.heycar.listing.service.ListingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller class which exposes APIs relates Listing service
 * 
 * @author Satya Kolipaka
 *
 */

@Api
@RestController
@RequestMapping("/hey-car")
public class ListingController {

	@Autowired
	private ListingService listingService;

	/**
	 * End point to create the Listings for specific Dealer
	 * 
	 * @param dealerId
	 * @param listingDtos
	 * @return
	 */
	@ApiOperation(value = "Create Listings", response = String.class, httpMethod = "POST")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully created Listings", response = String.class),
			@ApiResponse(code = 404, message = "Dealer not available", response = ErrorDetailsDto.class),
			@ApiResponse(code = 500, message = "Unexpected Internal Error", response = ErrorDetailsDto.class) })
	@PostMapping("/vehicle_listings/{dealerId}")
	public ResponseEntity<String> createListings(@PathVariable Long dealerId,
			@RequestBody List<ListingDto> listingDtos) {
		return new ResponseEntity<>(listingService.createListings(listingDtos, dealerId), HttpStatus.CREATED);
	}

	/**
	 * End point to create the Listings for specific Dealer via CSV upload
	 * 
	 * @param dealerId
	 * @param file
	 * @return
	 */
	@ApiOperation(value = "Create Listings via CSV file", response = String.class, httpMethod = "POST")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully created Listings", response = String.class),
			@ApiResponse(code = 404, message = "Dealer not available", response = ErrorDetailsDto.class),
			@ApiResponse(code = 400, message = "Bad type/format of CSV", response = ErrorDetailsDto.class),
			@ApiResponse(code = 500, message = "Unexpected Internal Error", response = ErrorDetailsDto.class) })
	@PostMapping("/upload_csv/{dealerId}")
	public ResponseEntity<String> createListingsCSV(@PathVariable Long dealerId,
			@RequestParam("file") MultipartFile file) {
		return new ResponseEntity<>(listingService.createListingsCSV(file, dealerId), HttpStatus.CREATED);
	}

	/**
	 * End point to retrieve the Listings for specific Dealer via CSV upload
	 * 
	 * @param params
	 * @return
	 */
	@ApiOperation(value = "Retrieve Listings", response = ListingDto.class, httpMethod = "GET", responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Listings", response = ListingDto.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad Request parameters for search", response = ErrorDetailsDto.class),
			@ApiResponse(code = 500, message = "Unexpected Internal Error", response = ErrorDetailsDto.class) })
	@GetMapping("/search")
	public ResponseEntity<List<ListingDto>> retrieveListings(@RequestParam Map<String, String> params) {
		return new ResponseEntity<>(listingService.retrieveListings(params), HttpStatus.OK);
	}
}
