package com.heycar.listing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heycar.listing.entity.Dealer;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {

}
