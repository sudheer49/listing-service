package com.heycar.listing.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.heycar.listing.dto.ListingDto;
import com.opencsv.bean.CsvToBeanBuilder;

public class ListingCSVConverter {

	public static List<ListingDto> convertCSVToListingCSV(final MultipartFile file) throws IOException {
		
		Reader reader = new InputStreamReader(file.getInputStream());
		return new CsvToBeanBuilder<ListingDto>(reader).withType(ListingDto.class).build().parse();

	}

}
