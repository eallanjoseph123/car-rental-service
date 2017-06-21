package com.online.rental.car.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.online.rental.car.dao.CarDao;
import com.online.rental.car.dao.EmailDao;
import com.online.rental.car.dao.ReservationDao;
import com.online.rental.car.model.Car;
import com.online.rental.car.model.Email;
import com.online.rental.car.model.EmailContent;
import com.online.rental.car.model.Reservation;
import com.online.rental.car.service.ReservationService;
import com.online.rental.car.util.CarAppUtil;
import com.online.rental.car.util.CarStatuType;

@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Value("${spring.mail.username}")
	private String username;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private ReservationDao reservationDao;
	
	@Autowired
	private CarDao carDao;
	
	@Autowired
	private EmailDao emailDao;
	
	/**
	 * Save transaction and send an email for more information
	 */
	//TODO: Make an asynchronous email sender
	@Override
	@Transactional
	public Reservation save(Reservation reservation) {
		Email email = new Email(javaMailSender);
		List<EmailContent> contents = emailDao.findAll();
		EmailContent content = contents.get(0);
		email.send(content);
		reservation.setReserveNumber(CarAppUtil.generateReserveNumber());
		return reservationDao.save(reservation);
	}
	
	@Override
	@Transactional
	public Reservation update(Reservation reservation) {	
		return reservationDao.save(reservation);
	}
	
	@Override
	@Transactional
	public Reservation delete(Long id) {
		Reservation reserve = reservationDao.findOne(id);
		reservationDao.delete(id);
		return reserve;
	}

	@Override
	@Transactional
	public List<Reservation> getAll() {
		return reservationDao.findAll();
	}

	@Override
	@Transactional
	public Car updateCarStatus(Long reservationId,CarStatuType type) {
		Reservation reserve = reservationDao.findOne(reservationId);
		Car car = reserve.getCar();
		car.setStatus(type.toString());
		return carDao.save(car);
	}

}
