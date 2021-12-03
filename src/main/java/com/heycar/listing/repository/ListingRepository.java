package com.heycar.listing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heycar.listing.entity.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

}
