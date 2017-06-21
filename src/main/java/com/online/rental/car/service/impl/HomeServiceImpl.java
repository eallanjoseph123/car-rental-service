package com.online.rental.car.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.rental.car.dao.CarDao;
import com.online.rental.car.model.CarAppResponse;
import com.online.rental.car.model.HomeView;
import com.online.rental.car.service.HomeService;

@Service
@Transactional
public class HomeServiceImpl implements HomeService<HomeView>{
	
	@Autowired
	private CarDao carDao;
	
	@Override
	public CarAppResponse<HomeView> getAvailableCars() {
		CarAppResponse<HomeView> response = new CarAppResponse<HomeView>();
		HomeView view = new HomeView();
		view.setCars(carDao.findAll());
		response.setResponse(view);
		return response;
	}

}
