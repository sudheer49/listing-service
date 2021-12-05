package com.heycar.listing.util;

import java.time.LocalDateTime;
import java.util.List;

import com.heycar.listing.dto.ListingDto;
import com.heycar.listing.entity.Dealer;
import com.heycar.listing.entity.Listing;

/**
 * Utility class for Unit tests
 * 
 * @author Satya Kolipaka
 *
 */
public class ListingServiceUtilTest {

	private ListingServiceUtilTest() {
		super();
	}

	public static List<ListingDto> buildListingDtos() {
		return List.of(buildListingDtoOne(), buildListingDtoTwo(), buildListingDtoThree());
	}

	public static List<Listing> buildListings() {
		return List.of(buildListingOne(), buildListingTwo(), buildListingThree());
	}

	public static Dealer buildDealer() {
		return new Dealer(1L, "Satya", LocalDateTime.now());
	}

	private static ListingDto buildListingDtoOne() {
		return new ListingDto("a", "renault", "megane", 132, 2014, "red", 13990);
	}

	private static ListingDto buildListingDtoTwo() {
		return new ListingDto("1", "mercedes", "a 180", 123, 2014, "black", 15950);
	}

	private static ListingDto buildListingDtoThree() {
		return new ListingDto("2", "vw", "golf", 86, 2018, "white", 17210);
	}

	private static Listing buildListingOne() {
		return new Listing(1L, "a", "renault", "megane", 132, 2014, "red", 13990, buildDealer(), LocalDateTime.now(),
				LocalDateTime.now());
	}

	private static Listing buildListingTwo() {
		return new Listing(2L, "1", "mercedes", "a 180", 123, 2014, "black", 15950, buildDealer(), LocalDateTime.now(),
				LocalDateTime.now());
	}

	private static Listing buildListingThree() {
		return new Listing(3L, "2", "vw", "golf", 86, 2018, "white", 17210, buildDealer(), LocalDateTime.now(),
				LocalDateTime.now());
	}
}
