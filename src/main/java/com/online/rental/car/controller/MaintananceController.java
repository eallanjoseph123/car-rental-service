package com.online.rental.car.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.online.rental.car.model.Car;
import com.online.rental.car.service.MaintananceService;

@Controller
@RequestMapping("/maintainance")
public class MaintananceController {
	
	//TODO: Employee should update the status of Car once the customer pick up the car
	
	@Autowired
	private MaintananceService maintananceService;
	
	@PostMapping(value = "/addCar",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Car addCar(@Valid @RequestBody Car car) {
		return  maintananceService.save(car);
	}
	
	@PostMapping(value = "/updateCar",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Car updateCar(@Valid @RequestBody Car car){
		return maintananceService.update(car);
	}
	
	@PostMapping(value = "/deleteCar/{carId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Car deleteCar(@PathVariable Long carId){
		return maintananceService.delete(carId);
	}
	
	@PostMapping(value = "/availableCars",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Car> availableCars(){
		return maintananceService.getAll();
	}
	
}
