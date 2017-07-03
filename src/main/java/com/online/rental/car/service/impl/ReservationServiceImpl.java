package com.online.rental.car.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
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
public class ReservationServiceImpl implements ReservationService {

	private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

	private static final Marker actionMarker = MarkerFactory.getMarker("ACTION");

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
	 * @throws Exception 
	 */
	// TODO: Make an asynchronous email sender
	@Override
	@Transactional
	public Reservation save(Reservation reservation) throws Exception {
		reservation.setReserveNumber(CarAppUtil.generateReserveNumber());
		Reservation oldTransaction = reservationDao.findByDriverLicenNumber(reservation.getDriverLicenNumber());
		Reservation transaction = null;
		try{
			if(null == oldTransaction){
				emailSent(transaction);
			}else{
				throw new RuntimeException("Licence number duplicated");
			}
		}catch(Exception e){
			//TODO: Make a handler for exception email either save or re-send it
		}finally{
			transaction = reservationDao.save(reservation);
			Car car = transaction.getCar();
			car.setStatus(CarStatuType.RENT.getTranslatedStatus());
			carDao.save(car);
			logger.debug(actionMarker,transaction.toString());
		}
		
		return transaction;
	}

	@Async
	private void emailSent(Reservation reservation) throws Exception{
		Email email = new Email(javaMailSender);
		List<EmailContent> contents = emailDao.findAll();
		EmailContent content = (null == contents) ? new EmailContent() : contents.get(0);
		StringBuilder message = new StringBuilder(content.getMessage());
		message.append(" Reserved number for your Car :").append(reservation.getReserveNumber());
		content.setMessage(message.toString());
		content.setReceiver(reservation.getEmail());
		email.send(content);
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
	public Car updateCarStatus(Long reservationId, CarStatuType type) {
		Reservation reserve = reservationDao.findOne(reservationId);
		Car car = reserve.getCar();
		car.setStatus(type.toString());
		return carDao.save(car);
	}

}
