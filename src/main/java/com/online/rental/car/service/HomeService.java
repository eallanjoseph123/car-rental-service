package com.online.rental.car.service;

import com.online.rental.car.model.CarAppResponse;

public interface HomeService <response extends CarApp> {
	public CarAppResponse<response> getAvailableCars();
}
