package com.online.rental.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
/**
 * Main Application Runner
 * @author User
 *
 */
@SpringBootApplication
@EnableAsync
public class ApplicationConfig {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationConfig.class, args);
	}
}
