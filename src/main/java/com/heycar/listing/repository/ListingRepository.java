package com.heycar.listing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heycar.listing.entity.Dealer;
import com.heycar.listing.entity.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
	
	List<Listing> findByCodeInAndDealer(List<String> code, Dealer dealer);
}
