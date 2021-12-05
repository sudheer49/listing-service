package com.heycar.listing.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.heycar.listing.dto.ListingDto;
import com.heycar.listing.entity.Dealer;
import com.heycar.listing.entity.Listing;
import com.heycar.listing.exception.DealerNotFountException;
import com.heycar.listing.exception.FileTypeException;
import com.heycar.listing.exception.InvalidSearchParamException;
import com.heycar.listing.repository.DealerRepository;
import com.heycar.listing.repository.ListingRepository;
import com.heycar.listing.util.ListingServiceUtilTest;

@RunWith(SpringRunner.class)
@SpringBootTest
class ListingServiceTest {

	@Autowired
	private ListingService listingService;

	@MockBean
	private ListingRepository listingRepositoryMock;

	@MockBean
	private DealerRepository dealerRepositoryMock;

	@Test
	@SuppressWarnings("unchecked")
	void createListingsTest_Success() {

		List<ListingDto> listingDtos = ListingServiceUtilTest.buildListingDtos();

		ArgumentCaptor<List<String>> argumentStrings = ArgumentCaptor.forClass(List.class);
		ArgumentCaptor<Dealer> argumentDealer = ArgumentCaptor.forClass(Dealer.class);
		ArgumentCaptor<List<Listing>> argumentListings = ArgumentCaptor.forClass(List.class);
		
		when(dealerRepositoryMock.findById(1L)).thenReturn(Optional.of(ListingServiceUtilTest.buildDealer()));

		String response = listingService.createListings(listingDtos, 1L);

		assertEquals("Listings are created successfully", response);
		verify(dealerRepositoryMock, times(1)).findById(1L);
		verify(listingRepositoryMock, times(1)).findByCodeInAndDealer(argumentStrings.capture(),
				argumentDealer.capture());
		verify(listingRepositoryMock, times(1)).saveAll(argumentListings.capture());

	}

	@Test
	void createListingsTest_Error() {
		List<ListingDto> listingDtos = ListingServiceUtilTest.buildListingDtos();

		assertThrows(DealerNotFountException.class, () -> {
			listingService.createListings(listingDtos, 1L);
		});
	}

	@Test
	@SuppressWarnings("unchecked")
	void createListingsCSVTest_Success() throws IOException {

		MockMultipartFile mockitoMultipartFile = new MockMultipartFile("test.csv", "test.csv", "text/csv",
				this.getClass().getClassLoader().getResourceAsStream("test.csv"));

		ArgumentCaptor<List<String>> argumentStrings = ArgumentCaptor.forClass(List.class);
		ArgumentCaptor<Dealer> argumentDealer = ArgumentCaptor.forClass(Dealer.class);
		ArgumentCaptor<List<Listing>> argumentListings = ArgumentCaptor.forClass(List.class);

		when(dealerRepositoryMock.findById(1L)).thenReturn(Optional.of(ListingServiceUtilTest.buildDealer()));

		String response = listingService.createListingsCSV(mockitoMultipartFile, 1L);

		assertEquals("Listings are created successfully", response);
		verify(dealerRepositoryMock, times(1)).findById(1L);
		verify(listingRepositoryMock, times(1)).findByCodeInAndDealer(argumentStrings.capture(),
				argumentDealer.capture());
		verify(listingRepositoryMock, times(1)).saveAll(argumentListings.capture());

	}

	@Test
	void createListingsCSVTest_Error() throws IOException {

		MockMultipartFile mockitoMultipartFile = new MockMultipartFile("file", "test.txt", MediaType.TEXT_PLAIN_VALUE,
				"Testing other than CSV files".getBytes());

		when(dealerRepositoryMock.findById(1L)).thenReturn(Optional.of(ListingServiceUtilTest.buildDealer()));
		assertThrows(FileTypeException.class, () -> {
			listingService.createListingsCSV(mockitoMultipartFile, 1L);
		});

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	void retriveListings_Success() {

		ArgumentCaptor<Specification> specificationsCaptor = ArgumentCaptor.forClass(Specification.class);
		Map<String, String> params = Map.of("make", "renault", "model", "megane", "year", "2018");
		when(listingRepositoryMock.findAll(specificationsCaptor.capture()))
				.thenReturn(ListingServiceUtilTest.buildListings());

		List<ListingDto> response = listingService.retriveListings(params);
		assertEquals(3, response.size());
		assertEquals("renault", response.get(0).getMake());
	}

	@Test
	void retriveListings_Error() {

		Map<String, String> params = Map.of("make", "renault", "price", "13990", "year", "2018");
		assertThrows(InvalidSearchParamException.class, () -> {
			listingService.retriveListings(params);
		});
	}

}
