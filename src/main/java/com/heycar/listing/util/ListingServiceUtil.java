package com.heycar.listing.util;

import com.heycar.listing.dto.ListingDto;
import com.heycar.listing.entity.Dealer;
import com.heycar.listing.entity.Listing;

public class ListingServiceUtil {

	private static final Double KW_TO_PS = 1.36;

	private ListingServiceUtil() {
		super();
	}

	public static Listing convertListingDtoToListing(ListingDto listingDto, Dealer dealer) {
		return new Listing(listingDto.getCode(), listingDto.getMake(), listingDto.getModel(), listingDto.getPower(),
				listingDto.getYear(), listingDto.getColor(), listingDto.getPrice(), dealer);
	}

	public static double convertKWtoPS(double power) {
		return KW_TO_PS * power;
	}
}
