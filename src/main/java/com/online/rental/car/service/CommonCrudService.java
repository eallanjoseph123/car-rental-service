package com.online.rental.car.service;

import java.util.List;

public interface CommonCrudService<T,K>{
	
	public T save(T t);
	
	public T update(T T);
	
	public T delete (K k);
	
	public List<T> getAll();

}
