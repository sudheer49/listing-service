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
	private double power;

	@CsvBindByName
	private int year;

	@CsvBindByName
	private String color;

	@CsvBindByName
	private double price;

	@JsonProperty("power-in-ps")
	public double getPower() {
		return power;
	}

	@JsonProperty("kW")
	public void setPower(double power) {
		this.power = power;
	}

}
