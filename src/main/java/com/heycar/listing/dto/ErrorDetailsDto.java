package com.heycar.listing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that holds error details
 * 
 * @author Satya Kolipaka
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDetailsDto {

	private int status;
	private String title;
	private String description;

}
