package com.heycar.listing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingDto {

	@CsvBindByName
	private String code;

	@CsvBindByName
	private String make;

	@CsvBindByName
	private String model;

	@CsvBindByName(column = "power-in-ps")
	@JsonProperty("kW")
	private double power;

	@CsvBindByName
	private int year;

	@CsvBindByName
	private String color;

	@CsvBindByName
	private double price;

}
