package com.online.rental.car.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.rental.car.dao.CarDao;
import com.online.rental.car.dao.ReservationDao;
import com.online.rental.car.model.Car;
import com.online.rental.car.model.Reservation;
import com.online.rental.car.service.EmailSenderService;
import com.online.rental.car.service.ReservationService;
import com.online.rental.car.util.CarAppUtil;
import com.online.rental.car.util.CarStatuType;

@Service
public class ReservationServiceImpl implements ReservationService {

	private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

	private static final Marker actionMarker = MarkerFactory.getMarker("ACTION");
	
	private static final SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy");

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private ReservationDao reservationDao;

	@Autowired
	private CarDao carDao;

	/**
	 * Save transaction and send an email for more information
	 * @throws Exception 
	 */
	// TODO: Make an asynchronous email sender
	@Override
	@Transactional
	public Reservation save(Reservation reservation){
		reservation.setReserveNumber(CarAppUtil.generateReserveNumber());
		Reservation oldTransaction = reservationDao.findByDriverLicenNumber(reservation.getDriverLicenNumber());
		Reservation transaction = null;
		//TODO: add handler to call the app error handler class
		try{
			//TODO: Make a handler to resent it again to clients
			boolean isSent = emailSenderService.sendEmail(reservation.getEmail(), reservation.getReserveNumber());
			if(null == oldTransaction){
				transaction = reservationDao.save(reservation);
				Car car = transaction.getCar();
				car.setStatus(CarStatuType.RENT.getTranslatedStatus());
				carDao.save(car);
				logger.debug(actionMarker,transaction.toString());
			}else{
				throw new RuntimeException("Licence number duplicated");
			}
		}catch(Exception e){
			//TODO: logger
		}finally{
			//TODO: Make a handler to resent it again to clients
		}
		
		return transaction;
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
