package com.online.rental.car.model;

import java.util.List;

import com.online.rental.car.service.CarApp;

public class HomeView implements CarApp{
	
	private List<Car> cars;

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

}
