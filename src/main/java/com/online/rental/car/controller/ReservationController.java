package com.online.rental.car.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.online.rental.car.model.Car;
import com.online.rental.car.model.Reservation;
import com.online.rental.car.service.ReservationService;
import com.online.rental.car.util.CarStatuType;

@RestController
@RequestMapping("/reserve")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	//TODO: send email once the due date of return is today (Implement quarts API to have an scheduler system sender)

	@PostMapping(value = "/addReservation",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Object addReservation(@Valid @RequestBody Reservation reservation) throws Exception {
		Reservation response = reservationService.save(reservation);
		if(response == null){
		   throw new Exception("reservation was not save");
		}
		return response;
	}
	
	@PostMapping(value = "/viewAllReservation",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Reservation> showAllReservation(){
		return reservationService.getAll();
	}
	
	@DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Reservation remove(@PathVariable Long id){
		return reservationService.delete(id);
	}
	

	/**
	 * Employee can update the status of Car once the customer pick up the car
	 * 
	 * @param reservationId
	 * @param statusType
	 * @return
	 */
	@PutMapping(value = "/update/car/{reservationId}/{statusType}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Car updateCarStatus(@PathVariable Long reservationId,@PathVariable Integer statusType){
		return reservationService.updateCarStatus(reservationId, CarStatuType.getTranslatedStatusByStatusName(statusType));
	}

	

}
