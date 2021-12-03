package com.heycar.listing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingDto {

	private String code;
	
	private String make;
	
	private String model;
	
	@JsonProperty("kW")
	private double power;
	
	private int year;
	
	private String color;
	
	private double price;
	
}
