package com.online.rental.car.util;

import java.util.HashMap;
import java.util.Map;

public enum CarStatuType {
	
	AVAILABLE(0, "Available"),
	
	RENT(1, "Rent"),
	
	UNKNOWN(-1, "Unknown Status");
	
	
	private static Map<Integer, CarStatuType> mapTranslatedStatus = new HashMap<Integer, CarStatuType>();
	
	private int code;
	
	private String translatedStatus;
	
	
	private CarStatuType(int code, String translatedStatus){
		this.code = code;
		this.translatedStatus = translatedStatus;
	}
	
	static {
		for(CarStatuType status : CarStatuType.values()){
			mapTranslatedStatus.put(status.getCode(), status);
		}
	}
	
	public int getCode(){
		return code;
	}
	
	public String getTranslatedStatus(){
		return translatedStatus;
	}
	
	public static CarStatuType getTranslatedStatusByStatusName(int statusCode){
		CarStatuType type = mapTranslatedStatus.get(statusCode);
		return ( null == type) ? CarStatuType.UNKNOWN : type;
	}
}
