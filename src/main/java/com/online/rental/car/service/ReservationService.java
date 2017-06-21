package com.online.rental.car.service;

import com.online.rental.car.model.Car;
import com.online.rental.car.model.Reservation;
import com.online.rental.car.util.CarStatuType;

public interface ReservationService extends CommonCrudService<Reservation, Long> {

	public Car updateCarStatus(Long reservationId, CarStatuType type);

}
