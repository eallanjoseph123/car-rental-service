package com.online.rental.car.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.online.rental.car.ApplicationConfig;
import com.online.rental.car.model.Car;
import com.online.rental.car.model.CarAppResponse;
import com.online.rental.car.model.HomeView;
import com.online.rental.car.service.HomeService;

@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
@WebAppConfiguration
public class HomeControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private HomeService<HomeView> homeService;
	
	@InjectMocks
	private HomeController homeController;
	
	
	
	@Before
	public void setUp() {
		 MockitoAnnotations.initMocks(this);
		 mockMvc = MockMvcBuilders.standaloneSetup(homeController)
				.setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(new AppErrorHandlerController()).build();
		
		 
		 List<Car> cars = new ArrayList<>();
			Car car1 = new Car();
			car1.setBrand("jeep");
			car1.setNumSittingCapacity(5);
			car1.setManual(false);
			car1.setOtherInfo("fast car :)");
			car1.setColor("blue");
			car1.setPlateNumber("A-112");
			car1.setPerDayPrice("10.00");
			car1.setStatus("AVAILABLE");
			 car1.setId(34L);
		 Car car2 = new Car("honda");
		 car2.setBrand("jeep");
		 car2.setNumSittingCapacity(5);
		 car2.setManual(false);
		 car2.setOtherInfo("fast car :)");
		 car2.setColor("blue");
		 car2.setPlateNumber("A-112");
		 car2.setPerDayPrice("10.00");
		 car2.setStatus("AVAILABLE");
		 car2.setId(33L);
		 cars.add(car1);
		 cars.add(car2);
		 
		 CarAppResponse<HomeView> response = new CarAppResponse<>();
		 HomeView hv = new HomeView();
		 hv.setCars(cars);
		 response.setResponse(hv);
		 Mockito.when(homeService.getAvailableCars()).thenReturn(response);
	}
	
	@Test
	public void showAvailableCarsTest() throws Exception{
		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/showAvailableCars");
		getRequest.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		
		ResultActions results = mockMvc.perform(getRequest);
		
		results.andDo(MockMvcResultHandlers.print());
		results.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		results.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
		results.andExpect(status().is2xxSuccessful());	
	}
}
