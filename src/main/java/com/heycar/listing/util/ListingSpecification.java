package com.heycar.listing.util;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.heycar.listing.entity.Listing;

public class ListingSpecification {

	private ListingSpecification() {
		super();
	}

	public static Specification<Listing> searchSepecification(Map<String, String> params) {
		Specification<Listing> specification = Specification.where(null);
		for (Map.Entry<String, String> param : params.entrySet()) {
			specification = specification.and(fieldSpecification(param.getKey(), param.getValue()));
		}
		return specification;
	}

	private static Specification<Listing> fieldSpecification(String parm, String value) {
		return (root, query, builder) -> builder.equal(root.get(parm), value);
	}
}
