package com.online.rental.car.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.online.rental.car.model.Reservation;
import com.online.rental.car.service.ReservationService;

@Controller
@RequestMapping("/reserve")
public class ReservationController {
	
	//@Autowired
	private ReservationService reservationService;
	
	//TODO: Client can reserve car
	//Pick up date and return date
	//update the car
	//send email once the due date of return is today
	
	//TODO: Employee can view the customers reservation
	
	
	@PostMapping(value = "/addReservation", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Object addReservation(@Valid @RequestBody Reservation reservation) throws Exception {
		Reservation response = reservationService.save(reservation);
		if(response == null){
		   throw new Exception("reservation was not save");
		}
		return response;
	}
	

}
