package com.online.rental.car.model;

public class Car {
	private String brand;
	
	public Car(){
		
	}

	public Car(String brand) {
		super();
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
