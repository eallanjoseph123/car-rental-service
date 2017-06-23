package com.online.rental.car.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.online.rental.car.ApplicationConfig;
import com.online.rental.car.model.Car;
import com.online.rental.car.model.Reservation;
import com.online.rental.car.util.CarStatuType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
@WebAppConfiguration
public class ReservationServiceTest {
	
	@Autowired
	private ReservationService reservationService;
	
	private Reservation reservation ;
	
	private Car car;
	
	public void setUp(){
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
		reservation.setAgeOfDriver(25);
		reservation.setDriverLicenNumber("driver123");
	}
	@Ignore
	@Test
	public void saveTest() throws Exception{
		reservationService.save(reservation);
	}
}
