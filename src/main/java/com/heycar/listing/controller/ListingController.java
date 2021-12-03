package com.heycar.listing.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.heycar.listing.dto.ListingDto;
import com.heycar.listing.service.ListingService;

@RestController
@RequestMapping("/hey-car")
public class ListingController {

	@Autowired
	private ListingService listingService;

	@PostMapping("/vehicle_listings/{dealerId}")
	public ResponseEntity<String> createListings(@PathVariable Long dealerId,
			@RequestBody List<ListingDto> listingDtos) {
		return new ResponseEntity<>(listingService.createListings(listingDtos, dealerId), HttpStatus.CREATED);
	}

	@PostMapping("/upload_csv/{dealerId}")
	public ResponseEntity<String> createListingsCSV(@PathVariable Long dealerId,
			@RequestParam("file") MultipartFile file) throws IOException {
		return new ResponseEntity<>(listingService.createListingsCSV(file, dealerId), HttpStatus.CREATED);
	}
}
