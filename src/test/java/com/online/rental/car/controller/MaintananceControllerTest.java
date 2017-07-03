package com.online.rental.car.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

import com.google.gson.Gson;
import com.online.rental.car.ApplicationConfig;
import com.online.rental.car.model.Car;
import com.online.rental.car.service.MaintananceService;

@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
@WebAppConfiguration
public class MaintananceControllerTest {
	
	private static final String ROOT_MAP = "/maintainance";
	
	private static final Gson utilGson = new Gson();
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private MaintananceController maintanceCtrl;
	
	@Mock
	private MaintananceService maintananceService;
	
	
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.standaloneSetup(maintanceCtrl)
				.setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(new AppErrorHandlerController()).build();
	
	}
	
	@Test
	public void addCarTest() throws Exception{
		String url = ROOT_MAP+"/addCar";
		
		Car car = new Car();
		car.setBrand("jeep");
		car.setNumSittingCapacity(5);
		car.setManual(false);
		car.setOtherInfo("fast car :)");
		car.setColor("blue");
		car.setPlateNumber("A-112");
		car.setPerDayPrice("10.00");
		car.setStatus("AVAILABLE");
		car.setType("vios");
		car.setId(33L);
		
		Mockito.when(maintananceService.save(Mockito.any(Car.class))).thenReturn(car);
		
		String jsonCar = utilGson.toJson(car);
		
		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post(url);
		getRequest.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		getRequest.content(jsonCar);
		ResultActions results = mockMvc.perform(getRequest);
		
		results.andDo(MockMvcResultHandlers.print());
		results.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		results.andExpect(MockMvcResultMatchers.jsonPath("$.brand").exists());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.numSittingCapacity").exists());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.manual").exists());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.otherInfo").exists());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.manual").isBoolean());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.numSittingCapacity", Matchers.is(5)));
		results.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	
		Mockito.verify(maintananceService,Mockito.times(1)).save(Mockito.any(Car.class));
	}
	
	@Test
	public void updateCarTest() throws Exception{
		String url = ROOT_MAP+"/updateCar";
		
		Car car = new Car();
		car.setBrand("jeep");
		car.setNumSittingCapacity(5);
		car.setManual(false);
		car.setOtherInfo("fast car :)");
		car.setColor("red");
		car.setPlateNumber("A-112");
		car.setPerDayPrice("10.00");
		car.setStatus("RENT");
		car.setType("vios");
		car.setId(22L);
		
		Mockito.when(maintananceService.update(Mockito.any(Car.class))).thenReturn(car);
		
		String jsonCar = utilGson.toJson(car);
		
		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.put(url);
		getRequest.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		getRequest.content(jsonCar);
		ResultActions results = mockMvc.perform(getRequest);
		
		results.andDo(MockMvcResultHandlers.print());
		results.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		results.andExpect(MockMvcResultMatchers.jsonPath("$.brand").exists());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.numSittingCapacity").exists());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.manual").exists());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.otherInfo").exists());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.manual").isBoolean());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.numSittingCapacity", Matchers.is(5)));
		results.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	
		Mockito.verify(maintananceService,Mockito.times(1)).update(Mockito.any(Car.class));
	}
	
	@Test
	public void deleteCarTest() throws Exception{
		long idCar = 5 ;
		String url = ROOT_MAP+"/deleteCar/"+idCar;
		
		Car car = new Car();
		car.setBrand("jeep");
		car.setNumSittingCapacity(5);
		car.setManual(false);
		car.setOtherInfo("fast car :)");
		car.setId(idCar);
		
		Mockito.when(maintananceService.delete(idCar)).thenReturn(car);
		
		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.delete(url);
		getRequest.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		ResultActions results = mockMvc.perform(getRequest);
		
		results.andDo(MockMvcResultHandlers.print());
		results.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		results.andExpect(MockMvcResultMatchers.jsonPath("$.brand").exists());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.numSittingCapacity").exists());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.manual").exists());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.otherInfo").exists());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.manual").isBoolean());
		results.andExpect(MockMvcResultMatchers.jsonPath("$.numSittingCapacity", Matchers.is(5)));
		results.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	
		Mockito.verify(maintananceService,Mockito.times(1)).delete(Mockito.anyLong());
	}
	
	@Test
	public void availableCarsTest() throws Exception{
		String url = ROOT_MAP+"/availableCars";
		
		List<Car> cars = new ArrayList<>();
		Car car1 = new Car(1,"toyota",true,5,"nice");
		Car car2 = new Car(3,"honda",false,4,"good");
		cars.add(car1);
		cars.add(car2);

		Mockito.when(maintananceService.getAll()).thenReturn(cars);

		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get(url);
		getRequest.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

		ResultActions results = mockMvc.perform(getRequest);
		
		
		results.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		results.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
		results.andExpect(status().is2xxSuccessful());	
		results.andDo(MockMvcResultHandlers.print());
		
		Mockito.verify(maintananceService,Mockito.times(1)).getAll();
	}
	
	

}
