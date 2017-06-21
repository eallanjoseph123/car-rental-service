package com.online.rental.car.util;

import java.util.Random;

public class CarAppUtil {
	
	private CarAppUtil(){}
	
	public static String generateReserveNumber(){
		Random ran = new Random();
		int number = ran.nextInt(6) + Integer.MAX_VALUE;
		StringBuilder sb = new StringBuilder(Constants.CRS.toString()).append(number);
		return sb.toString(); 
	} 
}
