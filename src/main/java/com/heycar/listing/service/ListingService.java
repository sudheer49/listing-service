package com.heycar.listing.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.heycar.listing.dto.ListingDto;
import com.heycar.listing.entity.Dealer;
import com.heycar.listing.entity.Listing;
import com.heycar.listing.exception.DealerNotFountException;
import com.heycar.listing.exception.InvalidSearchParamException;
import com.heycar.listing.repository.DealerRepository;
import com.heycar.listing.repository.ListingRepository;
import com.heycar.listing.util.ListingCSVConverter;
import com.heycar.listing.util.ListingServiceUtil;
import com.heycar.listing.util.ListingSpecification;

import lombok.extern.slf4j.Slf4j;

/**
 * Service class which is having all the methods related to ListingController
 * 
 * @author Satya Kolipaka
 *
 */
@Slf4j
@Service
public class ListingService {

	private ListingRepository listingRepository;

	private DealerRepository dealerRepository;

	@Value("${search-parms}")
	private List<String> searchParms;

	@Autowired
	public ListingService(ListingRepository listingRepository, DealerRepository dealerRepository) {
		this.listingRepository = listingRepository;
		this.dealerRepository = dealerRepository;
	}

	/**
	 * Create or Update Listings for specific Dealer. It converts KW to PS before
	 * create listings
	 * 
	 * @param listingDtos
	 * @param dealerId
	 * @return
	 */
	public String createListings(List<ListingDto> listingDtos, Long dealerId) {

		listingDtos.stream().forEach(l -> l.setPower(ListingServiceUtil.convertKWtoPS(l.getPower())));
		persistListings(listingDtos, dealerId);

		return "Listings are created successfully";
	}

	/**
	 * Create or Update Listings for specific Dealer. It converts CSV file to
	 * Listing entity
	 * 
	 * @param file
	 * @param dealerId
	 * @return
	 */
	public String createListingsCSV(MultipartFile file, Long dealerId) {

		List<ListingDto> listingDtos = ListingCSVConverter.convertCSVToListingDto(file);
		persistListings(listingDtos, dealerId);

		return "Listings are created successfully";
	}

	/**
	 * Retrieve all available Listings on the system based on search params
	 * 
	 * @param params
	 * @return
	 */
	public List<ListingDto> retrieveListings(Map<String, String> params) {

		if (!searchParms.containsAll(params.keySet())) {
			throw new InvalidSearchParamException(String.format("Allowed parameters for serach are %s", searchParms));
		}

		List<Listing> listings = listingRepository.findAll(ListingSpecification.searchSepecification(params));
		return listings.stream().map(ListingServiceUtil::convertListingToListingDto).collect(Collectors.toList());
	}

	/**
	 * Method to convert List of ListingDto to entities and Persist data in DB
	 * 
	 * @param listingDtos
	 * @param dealerId
	 */
	private void persistListings(List<ListingDto> listingDtos, Long dealerId) {

		Optional<Dealer> dealer = dealerRepository.findById(dealerId);
		if (dealer.isEmpty()) {
			log.error("There is no Dealer present with delear id:{} ", dealerId);
			throw new DealerNotFountException(
					String.format("There is no Dealer present with delear id: %s ", dealerId));
		}

		List<Listing> availableListings = listingRepository.findByCodeInAndDealer(
				listingDtos.stream().map(ListingDto::getCode).collect(Collectors.toList()), dealer.get());

		availableListings.forEach(a -> {
			ListingDto lDto = listingDtos.stream().filter(l -> l.getCode().equals(a.getCode())).findFirst().get();
			ListingServiceUtil.updateListingWithDto(a, lDto);
			listingDtos.remove(lDto);
		});

		List<Listing> listings = listingDtos.stream()
				.map(listing -> ListingServiceUtil.convertListingDtoToListing(listing, dealer.get()))
				.collect(Collectors.toList());

		listings.addAll(availableListings);

		log.info("Persisting Listings in DB for Dealer {} ", dealerId);

		listingRepository.saveAll(listings);

		log.info("Successfully persited {} Listings in DB", listings.size());
	}

}
