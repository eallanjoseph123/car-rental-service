package com.online.rental.car.controller;

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

@RunWith(MockitoJUnitRunner.class)
// @RunWith(SpringRunner.class)
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

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(reservationController)
				.setMessageConverters(new MappingJackson2HttpMessageConverter())
				.setControllerAdvice(new AppErrorHandlerController()).build();

	}
	
	@Test
	public void updateCarStatus(){
		Car car2 = new Car();
		car2.setBrand("jeep");
		car2.setNumSittingCapacity(5);
		car2.setManual(false);
		car2.setOtherInfo("fast car :)");
		car2.setColor("blue");
		car2.setPlateNumber("A-112");
		car2.setPerDayPrice("10.00");
		car2.setId(33L);
		car2.setStatus("RENT");
		Mockito.when(reservationService.updateCarStatus(Mockito.anyLong())).thenReturn(car2);
		
		
		Car car = new Car();
		car.setBrand("jeep");
		car.setNumSittingCapacity(5);
		car.setManual(false);
		car.setOtherInfo("fast car :)");
		car.setColor("blue");
		car.setPlateNumber("A-112");
		car.setPerDayPrice("10.00");
		car.setStatus("AVAILABLE");
		car2.setId(32L);
		
		Reservation r = new Reservation();
		r.setAgeOfDriver(25);
		r.setDriverLicenNumber("driver123");
		r.setCar(car);
		r.setPickUpDate("11-06-2016");
		r.setReturnDate("11-07-2016");
		r.setTotalPrice("20.00");
		r.setId(100);
		Mockito.when(reservationDao.findOne(Mockito.anyLong())).thenReturn(r);
		
		
		
		
		
		Long reservationId = 100L;
		Reservation r2 = reservationDao.findOne(reservationId);
		
		Car c3 = r2.getCar();
		String anotherString = "AVAILABLE";
		
		Assert.assertTrue(c3.getStatus().equalsIgnoreCase(anotherString));
		
		Car c2 = reservationService.updateCarStatus(reservationId);
		
		Assert.assertFalse(c3.getStatus().equalsIgnoreCase(c2.getStatus()));
		
	}

	@Test
	public void createReservationTest() throws Exception{
		String url = ROOT_MAP+"/addReservation";
		
		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post(url);
		
		Reservation r = new Reservation();
		r.setAgeOfDriver(25);
		r.setDriverLicenNumber("driver123");
		Car car = new Car();
		car.setBrand("jeep");
		car.setNumSittingCapacity(5);
		car.setManual(false);
		car.setOtherInfo("fast car :)");
		car.setColor("blue");
		car.setPlateNumber("A-112");
		car.setId(32L);
		r.setCar(car);
		r.setPickUpDate("11-06-2016");
		r.setReturnDate("11-07-2016");
		r.setTotalPrice("20.00");
		r.setId(11L);
	
		Mockito.when(reservationService.save(Mockito.any(Reservation.class))).thenReturn(r);
		
		
		String jsonReserve = utilGson.toJson(r);
		
		getRequest.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		getRequest.content(jsonReserve);
		ResultActions results = mockMvc.perform(getRequest);
		
		results.andDo(MockMvcResultHandlers.print());
		results.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	
	}
}
