package com.online.rental.car.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Car", schema = "rental")
public class Car {
	
	@Id
    @GeneratedValue
    @Column(name = "car_id")
	private Long id;
	
	@NotBlank(message = "must not be blank")
	@Column(name = "brand")
	private String brand;
	
	@NotBlank(message = "must not be blank")
	@Column(name = "color")
	private String color;
	
	@NotNull
	@Column(name = "manual")
	private boolean isManual;
	
	@NotNull
    @Min(2)
	@Column(name = "capacity")
	private int numSittingCapacity;
	
	@Column(name = "otherInfo")
	private String otherInfo;
	
	@NotBlank(message = "must not be blank")
	@Column(name = "plateNumber", unique = true)
	private String plateNumber;
	
	@NotBlank(message = "must not be blank")
	@Column(name = "price")
	private String perDayPrice;
	
	@NotBlank(message = "must not be blank")
	@Column(name = "status")
	private String status;

	public Car(){
		
	}

	public Car(String brand) {
		super();
		this.brand = brand;
	}

	public Car(long id, String brand, boolean isManual, int numSittingCapacity, String otherInfo) {
		super();
		this.id = id;
		this.brand = brand;
		this.isManual = isManual;
		this.numSittingCapacity = numSittingCapacity;
		this.otherInfo = otherInfo;
	}
	

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPerDayPrice() {
		return perDayPrice;
	}

	public void setPerDayPrice(String perDayPrice) {
		this.perDayPrice = perDayPrice;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public boolean isManual() {
		return isManual;
	}

	public void setManual(boolean isManual) {
		this.isManual = isManual;
	}

	public int getNumSittingCapacity() {
		return numSittingCapacity;
	}

	public void setNumSittingCapacity(int numSittingCapacity) {
		this.numSittingCapacity = numSittingCapacity;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	
	

}
