package com.online.rental.car.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailConfigProp {
	
	@Value("${spring.mail.username}")
	private String username;

	public String getUsername() {
		return username;
	}
	
	
}
