package com.online.rental.car.dao;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.online.rental.car.ApplicationConfig;
import com.online.rental.car.model.Reservation;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
@WebAppConfiguration
public class ReservationDaoTest {
	
	@Autowired
	private ReservationDao reservationDao;
	
	@Ignore
	@Test
	@Transactional
	public void findByDriverLicenceNumberTest(){
      	Reservation reserve = reservationDao.findByDriverLicenNumber("driver123");
		Assert.assertNotNull(reserve);
	}
	
	
	
}
