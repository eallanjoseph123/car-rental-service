package com.online.rental.car.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.online.rental.car.model.Car;
import com.online.rental.car.model.CarAppResponse;
import com.online.rental.car.model.HomeView;
import com.online.rental.car.service.HomeService;

@Controller
public class HomeController {
	
	//@Autowired
	private HomeService<HomeView> homeService;
	
	@GetMapping("/")
	public String homepage() {
		return "index";
	}
	
	@GetMapping(value = "/showAvailableCars",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Car> showAvailableCars() {
		CarAppResponse<HomeView> response = homeService.getAvailableCars();
		HomeView homeView= response.getPayLoad();
		return homeView.getCars();
	}
	
	

	

}
