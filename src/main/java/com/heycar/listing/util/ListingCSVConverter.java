package com.heycar.listing.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.heycar.listing.dto.ListingDto;
import com.heycar.listing.exception.CSVConverterException;
import com.heycar.listing.exception.FileTypeException;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListingCSVConverter {

	private static final String FILE_TYPE = "csv";

	private ListingCSVConverter() {
		super();
	}

	public static List<ListingDto> convertCSVToListingDto(final MultipartFile file) {

		if (!FILE_TYPE.equalsIgnoreCase(StringUtils.getFilenameExtension(file.getOriginalFilename()))) {
			log.error("File type is not CSV type");
			throw new FileTypeException();
		}

		Reader reader;
		List<ListingDto> listingDtos;
		try {
			reader = new InputStreamReader(file.getInputStream());
			listingDtos = new CsvToBeanBuilder<ListingDto>(reader).withType(ListingDto.class).build().parse();
			if (listingDtos.isEmpty()) {
				throw new IOException();
			}
		} catch (IOException e) {
			log.error("Error while reading the csv file " + e.getMessage());
			throw new CSVConverterException();
		}

		return listingDtos;
	}
}
