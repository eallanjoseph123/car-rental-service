package com.online.rental.car.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.online.rental.car.ApplicationConfig;
import com.online.rental.car.dao.ReservationDao;
import com.online.rental.car.model.Car;
import com.online.rental.car.model.Reservation;
import com.online.rental.car.service.ReservationService;
import com.online.rental.car.util.CarStatuType;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
@WebAppConfiguration
public class ReservationControllerTest {

	private static final String ROOT_MAP = "/reserve";

	private static final Gson utilGson = new Gson();

	private MockMvc mockMvc;

	@InjectMocks
	private ReservationController reservationController;
	
	@Mock
	private ReservationService reservationService;
	
	@Mock
	private ReservationDao reservationDao;
	
	private Reservation reservation ;
	
	private Reservation reservation2 ;
	
	private Car car;
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(reservationController)
				.setMessageConverters(new MappingJackson2HttpMessageConverter())
				.setControllerAdvice(new AppErrorHandlerController()).build();
		
		reservation = new Reservation();
		reservation.setAgeOfDriver(25);
		reservation.setDriverLicenNumber("driver123");
		car = new Car();
		car.setBrand("jeep");
		car.setNumSittingCapacity(5);
		car.setManual(false);
		car.setOtherInfo("fast car :)");
		car.setColor("blue");
		car.setPlateNumber("A-112");
		car.setId(32L);
		car.setStatus(CarStatuType.AVAILABLE.toString());
		reservation.setCar(car);
		reservation.setPickUpDate("11-06-2016");
		reservation.setReturnDate("11-07-2016");
		reservation.setTotalPrice("20.00");
		reservation.setId(11L);
		reservation.setEmail("email@yahoo.com");
		
		reservation2 = new Reservation();
		reservation.setAgeOfDriver(25);
		reservation.setDriverLicenNumber("driver123");
		Car car2 = new Car();
		car2.setBrand("jeep");
		car2.setNumSittingCapacity(5);
		car2.setManual(false);
		car2.setOtherInfo("fast car :)");
		car2.setColor("blue");
		car2.setPlateNumber("A-112");
		car2.setId(33L);
		reservation2.setCar(car);
		reservation2.setPickUpDate("11-06-2016");
		reservation2.setReturnDate("11-07-2016");
		reservation2.setTotalPrice("20.00");
		reservation2.setId(12L);
	}
	
	@Test
	public void updateCarStatus() throws Exception{
		String url = ROOT_MAP+"/update/car/"+11+"/"+1;
		
		Car car2 = new Car();
		car2.setBrand("jeep");
		car2.setNumSittingCapacity(5);
		car2.setManual(false);
		car2.setOtherInfo("fast car :)");
		car2.setColor("blue");
		car2.setPlateNumber("A-112");
		car2.setPerDayPrice("10.00");
		car2.setId(33L);
		car2.setStatus(CarStatuType.RENT.toString());
		
		Mockito.when(reservationService.updateCarStatus(11L,CarStatuType.RENT)).thenReturn(car2);
		
		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.put(url);
		ResultActions results = mockMvc.perform(getRequest);
		
		results.andDo(MockMvcResultHandlers.print());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.brand").exists());
		results.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		
		Car car3 = reservationService.updateCarStatus(11L,CarStatuType.RENT);
		
		Assert.assertFalse(car.getStatus().equalsIgnoreCase(car3.getStatus()));
	}

	@Test
	public void createReservationTest() throws Exception{
		String url = ROOT_MAP+"/addReservation";
		
		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post(url);
		Mockito.when(reservationService.save(Mockito.any(Reservation.class))).thenReturn(reservation);
		
		
		String jsonReserve = utilGson.toJson(reservation);
		
		getRequest.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		getRequest.content(jsonReserve);
		ResultActions results = mockMvc.perform(getRequest);
		
		results.andDo(MockMvcResultHandlers.print());
		results.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	
	}
	
	@Test
	public void showAllReservationTest() throws Exception{
		String url = ROOT_MAP+"/viewAllReservation";
		
		List<Reservation> value = new ArrayList<Reservation>();
		value.add(reservation);
		value.add(reservation2);
		Mockito.when(reservationService.getAll()).thenReturn(value);
		
		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get(url);
		ResultActions results = mockMvc.perform(getRequest);
		results.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		results.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
		results.andExpect(status().is2xxSuccessful());	
		results.andDo(MockMvcResultHandlers.print());
		
		Mockito.verify(reservationService,Mockito.atLeast(1)).getAll();
	}
	
	
	@Test
	public void removeTest() throws Exception{
		String url = ROOT_MAP+"/delete/"+reservation.getId();
		Mockito.when(reservationService.delete(reservation.getId())).thenReturn(reservation);
		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.delete(url);
		
		ResultActions results = mockMvc.perform(getRequest);
		
		results.andExpect(status().is2xxSuccessful());	
		results.andDo(MockMvcResultHandlers.print());
	}
}
