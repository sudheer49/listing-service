package com.heycar.listing.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.heycar.listing.dto.ListingDto;
import com.heycar.listing.entity.Dealer;
import com.heycar.listing.entity.Listing;
import com.heycar.listing.repository.DealerRepository;
import com.heycar.listing.repository.ListingRepository;
import com.heycar.listing.util.ListingCSVConverter;
import com.heycar.listing.util.ListingServiceUtil;

@Service
public class ListingService {

	private ListingRepository listingRepository;

	private DealerRepository dealerRepository;
	
	@Autowired
	public ListingService(ListingRepository listingRepository, DealerRepository dealerRepository) {
		this.listingRepository = listingRepository;
		this.dealerRepository = dealerRepository;
	}

	public String createListings(List<ListingDto> listingDtos, Long dealerId) {

		listingDtos.stream().forEach(l -> l.setPower(ListingServiceUtil.convertKWtoPS(l.getPower())));
		persistListings(listingDtos,dealerId);

		return "Listings are created successfully";
	}

	public String createListingsCSV(MultipartFile file, Long dealerId) throws IOException {

		List<ListingDto> listingDtos = ListingCSVConverter.convertCSVToListingCSV(file);
		persistListings(listingDtos,dealerId);
		
		return "Listings are created successfully";
	}
	
	private void persistListings(List<ListingDto> listingDtos, Long dealerId) {
		Dealer dealer = dealerRepository.findById(dealerId).get();
		List<Listing> listings = listingDtos.stream()
				.map(listing -> ListingServiceUtil.convertListingDtoToListing(listing, dealer))
				.collect(Collectors.toList());
		listingRepository.saveAll(listings);
	}

}
