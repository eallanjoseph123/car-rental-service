package com.online.rental.car.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.online.rental.car.model.Reservation;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Long>,JpaSpecificationExecutor<Reservation>{		
	@Query("SELECT r FROM Reservation r WHERE r.driverLicenNumber = :driverLicenNumber")
	public Reservation findByDriverLicenNumber(@Param("driverLicenNumber") String driverLicenNumber);
}
