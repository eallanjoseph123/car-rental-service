package com.online.rental.car.service;

import com.online.rental.car.model.Car;
import com.online.rental.car.model.Reservation;

public interface ReservationService extends CommonCrudService<Reservation,Long>{

	public Car updateCarStatus(Long reservationId);

}
