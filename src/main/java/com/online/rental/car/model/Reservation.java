package com.online.rental.car.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "reservation", schema = "rental")
public class Reservation {
	
	
	@Id
    @GeneratedValue
    @Column(name = "RESERVE_ID")
	private Long id;
	
	@NotBlank(message = "must not be blank")
	@Column(name = "pickUpDate", unique = true)
	private String pickUpDate;
	
	@NotBlank(message = "must not be blank")
	@Column(name = "returnDate", unique = true)
	private String returnDate;
	

	@NotNull(message = "must not be blank")
	@OneToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
	@NotBlank(message = "must not be blank")
	@Column(name = "driverLicenNumber", unique = true)
	private String driverLicenNumber;
	
    @Min(20)
    @Column(name = "ageOfDriver")
	private int ageOfDriver;
    
    /**
     * Computation of this price will be on frontEnd using angularJS
     * 
     * Formula
     * Days * carPrice
     */
    @NotBlank(message = "must not be blank")
    @Column(name = "totalPrice")
    private String totalPrice;
    
    @Column(name = "reserveNumber", unique = true)
    private String reserveNumber;
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getReserveNumber() {
		return reserveNumber;
	}

	public void setReserveNumber(String reserveNumber) {
		this.reserveNumber = reserveNumber;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(String pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getDriverLicenNumber() {
		return driverLicenNumber;
	}

	public void setDriverLicenNumber(String driverLicenNumber) {
		this.driverLicenNumber = driverLicenNumber;
	}

	public int getAgeOfDriver() {
		return ageOfDriver;
	}

	public void setAgeOfDriver(int ageOfDriver) {
		this.ageOfDriver = ageOfDriver;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
