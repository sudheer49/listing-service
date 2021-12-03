package com.heycar.listing.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Listing {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String code;

	@Column
	private String make;

	@Column
	private String model;

	@Column
	private double power;

	@Column
	private int year;

	@Column
	private String color;

	@Column
	private double price;

	@ManyToOne
	private Dealer dealer;

	@Column
	private LocalDateTime createdOn = LocalDateTime.now();

	@Column
	private LocalDateTime updatedOn = LocalDateTime.now();

	public Listing(String code, String make, String model, double power, int year, String color, double price,
			Dealer dealer) {
		this.code = code;
		this.make = make;
		this.model = model;
		this.power = power;
		this.year = year;
		this.color = color;
		this.price = price;
		this.dealer = dealer;
	}

}
