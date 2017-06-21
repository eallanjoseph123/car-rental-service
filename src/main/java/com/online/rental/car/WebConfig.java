package com.online.rental.car;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter {
	
	private static final String VIEWS = "/";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/");
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix(VIEWS);
		internalResourceViewResolver.setSuffix(".html");
		return internalResourceViewResolver;
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	  //  registry.addViewController("/").setViewName("forward:/index.html");
	}
}