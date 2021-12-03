package com.heycar.listing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heycar.listing.dto.ListingDto;
import com.heycar.listing.entity.Dealer;
import com.heycar.listing.entity.Listing;
import com.heycar.listing.repository.DealerRepository;
import com.heycar.listing.repository.ListingRepository;
import com.heycar.listing.util.ListingServiceUtil;

@Service
public class ListingService {

	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private DealerRepository dealerRepository;
	
	public String createListings(List<ListingDto> listingDtos, Long dealerId) {
		
		Dealer dealer = dealerRepository.findById(dealerId).get();
		List<Listing> listings = listingDtos.stream().map(listing -> ListingServiceUtil.convertListingDtoToListing(listing, dealer)).collect(Collectors.toList());
		listingRepository.saveAll(listings);
		
		return "Listings are created successfully";
	}
	
	
}
