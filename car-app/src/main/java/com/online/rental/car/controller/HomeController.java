package com.online.rental.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.online.rental.car.model.CarAppResponse;
import com.online.rental.car.model.HomeView;
import com.online.rental.car.service.HomeService;

@RestController
public class HomeController {
	
	@Autowired
	private HomeService<HomeView> homeService;
	
	@GetMapping(value = "/showAvailableCars",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Object showAvailableCars() {
		CarAppResponse<HomeView> response = homeService.getAvailableCars();
		HomeView homeView= response.getPayLoad();
		return homeView.getCars();
	}
	
	
	

}
