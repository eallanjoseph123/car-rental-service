package com.online.rental.car.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.online.rental.car.model.Car;
import com.online.rental.car.model.CarAppResponse;
import com.online.rental.car.model.HomeView;
import com.online.rental.car.service.HomeService;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private static final Marker actionMarker = MarkerFactory.getMarker("ACTION");
	
	@Autowired
	private HomeService<HomeView> homeService;
	
	@GetMapping(value = "/showAvailableCars",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Car> showAvailableCars() {
		CarAppResponse<HomeView> response = homeService.getAvailableCars();
		HomeView homeView= response.getPayLoad();
		response.setResponse(homeView);
		List<Car> cars =homeView.getCars();
		Collections.sort(cars);
		logger.debug(actionMarker,"view all cars {}",homeView.getCars());
		return cars;
	}
	
	

	

}
