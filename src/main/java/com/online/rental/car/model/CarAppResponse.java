package com.online.rental.car.model;

public class CarAppResponse<T> {

	private T response;

	public T getPayLoad() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

}
