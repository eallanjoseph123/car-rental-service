package com.online.rental.car.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.rental.car.dao.CarDao;
import com.online.rental.car.model.Car;
import com.online.rental.car.service.MaintananceService;

@Service
public class MaintananceServiceImpl implements MaintananceService{
	
	@Autowired
	private CarDao carDao;

	@Override
	@Transactional
	public Car save(Car car) {
		return carDao.save(car);
	}

	@Override
	@Transactional
	public Car update(Car car) {
		return carDao.save(car);
	}

	@Override
	@Transactional
	public Car delete(Long id) {
		Car car = carDao.findOne(id);
		carDao.delete(id);
		return car;
	}

	@Override
	@Transactional
	public List<Car> getAll() {
		return carDao.findAll();
	}

}
