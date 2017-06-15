package com.online.rental.car.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.rental.car.model.Reservation;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Long>{

}
