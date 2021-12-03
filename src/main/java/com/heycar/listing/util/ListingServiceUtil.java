package com.heycar.listing.util;

import com.heycar.listing.dto.ListingDto;
import com.heycar.listing.entity.Dealer;
import com.heycar.listing.entity.Listing;

public class ListingServiceUtil {

	private ListingServiceUtil() {
		super();
	}

	public static Listing convertListingDtoToListing(ListingDto listingDto, Dealer dealer) {
		return new Listing(listingDto.getCode(), listingDto.getMake(), listingDto.getModel(), listingDto.getPower(),
				listingDto.getYear(), listingDto.getColor(), listingDto.getPrice(), dealer);
	}

	public static double convertKWtoPS(double power) {
		return 1.36 * power;
	}
}
