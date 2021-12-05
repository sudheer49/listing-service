package com.heycar.listing.util;

import java.time.LocalDateTime;

import com.heycar.listing.dto.ListingDto;
import com.heycar.listing.entity.Dealer;
import com.heycar.listing.entity.Listing;

/**
 * Utility class for Listing Service
 * 
 * @author Satya Kolipaka
 *
 */
public class ListingServiceUtil {

	private static final Double KW_TO_PS = 1.36;

	private ListingServiceUtil() {
		super();
	}

	public static Listing convertListingDtoToListing(ListingDto listingDto, Dealer dealer) {
		return new Listing(listingDto.getCode(), listingDto.getMake(), listingDto.getModel(), listingDto.getPower(),
				listingDto.getYear(), listingDto.getColor(), listingDto.getPrice(), dealer);
	}

	public static Listing updateListingWithDto(Listing listing, ListingDto listingDto) {
		listing.setColor(listingDto.getColor());
		listing.setMake(listingDto.getMake());
		listing.setModel(listingDto.getModel());
		listing.setPower(listingDto.getPower());
		listing.setYear(listingDto.getYear());
		listing.setPrice(listingDto.getPrice());
		listing.setUpdatedOn(LocalDateTime.now());
		return listing;
	}

	public static ListingDto convertListingToListingDto(Listing listing) {
		return new ListingDto(listing.getCode(), listing.getMake(), listing.getModel(), listing.getPower(),
				listing.getYear(), listing.getColor(), listing.getPrice());
	}

	public static double convertKWtoPS(double power) {
		return KW_TO_PS * power;
	}
}
