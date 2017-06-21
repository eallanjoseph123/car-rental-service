package com.online.rental.car.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.rental.car.model.EmailContent;



@Repository
public interface EmailDao extends JpaRepository<EmailContent, Long>{

}
