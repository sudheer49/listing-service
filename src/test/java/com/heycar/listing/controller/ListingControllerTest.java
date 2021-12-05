package com.heycar.listing.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.heycar.listing.exception.DealerNotFountException;
import com.heycar.listing.exception.ExceptionControllerAdvice;
import com.heycar.listing.service.ListingService;
import com.heycar.listing.util.ListingServiceUtilTest;

/**
 * Unit Test class for ListingController
 * 
 * @author Satya Kolipaka
 *
 */
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class ListingControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ListingService listingServiceMock;

	@BeforeEach
	public void setup() {
		ListingController listingController = new ListingController();
		ReflectionTestUtils.setField(listingController, "listingService", listingServiceMock);
		this.mockMvc = MockMvcBuilders.standaloneSetup(listingController)
				.setControllerAdvice(new ExceptionControllerAdvice()).build();
	}

	@SuppressWarnings("unchecked")
	@Test
	void createListingsTest_Success() throws Exception {
		String json = "[\r\n" + "{\r\n" + "\"code\": \"d\",\r\n" + "\"make\": \"renault\",\r\n"
				+ "\"model\": \"megane\",\r\n" + "\"kW\": 132,\r\n" + "\"year\": 2014,\r\n"
				+ "\"color\": \"green\",\r\n" + "\"price\": 13990\r\n" + "},\r\n" + "{\r\n" + "\"code\": \"b\",\r\n"
				+ "\"make\": \"audi\",\r\n" + "\"model\": \"a3\",\r\n" + "\"kW\": 111,\r\n" + "\"year\": 2018,\r\n"
				+ "\"color\": \"green\",\r\n" + "\"price\": 17210\r\n" + "}\r\n" + "]";

		when(listingServiceMock.createListings(any(List.class), any(Long.class)))
				.thenReturn("Listings are created successfully");

		this.mockMvc
				.perform(post("/hey-car/vehicle_listings/{dealerId}", 1L).contentType(MediaType.APPLICATION_JSON)
						.content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$").value("Listings are created successfully"));
	}

	@SuppressWarnings("unchecked")
	@Test
	void createListingsTest_Error() throws Exception {
		String json = "[\r\n" + "{\r\n" + "\"code\": \"d\",\r\n" + "\"make\": \"renault\",\r\n"
				+ "\"model\": \"megane\",\r\n" + "\"kW\": 132,\r\n" + "\"year\": 2014,\r\n"
				+ "\"color\": \"green\",\r\n" + "\"price\": 13990\r\n" + "},\r\n" + "{\r\n" + "\"code\": \"b\",\r\n"
				+ "\"make\": \"audi\",\r\n" + "\"model\": \"a3\",\r\n" + "\"kW\": 111,\r\n" + "\"year\": 2018,\r\n"
				+ "\"color\": \"green\",\r\n" + "\"price\": 17210\r\n" + "}\r\n" + "]";

		when(listingServiceMock.createListings(any(List.class), any(Long.class)))
				.thenThrow(DealerNotFountException.class);

		this.mockMvc
				.perform(post("/hey-car/vehicle_listings/{dealerId}", 5L).contentType(MediaType.APPLICATION_JSON)
						.content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.title").value("Dealer not available"));
	}

	@SuppressWarnings("unchecked")
	@Test
	void retriveListings_Success() throws Exception {

		when(listingServiceMock.retrieveListings(any(Map.class))).thenReturn(ListingServiceUtilTest.buildListingDtos());

		this.mockMvc.perform(get("/hey-car/search?make=vw&model=golf&year=2018&color=green")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].code").value("a")).andExpect(jsonPath("$.[0].model").value("megane"))
				.andExpect(jsonPath("$.[1].make").value("mercedes"))
				.andExpect(jsonPath("$.[1].power-in-ps").value("123.0")).andExpect(jsonPath("$.[2].year").value("2018"))
				.andExpect(jsonPath("$.[2].color").value("white"));
	}
}
