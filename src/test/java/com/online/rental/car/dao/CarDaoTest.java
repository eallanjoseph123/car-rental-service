package com.online.rental.car.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.online.rental.car.ApplicationConfig;
import com.online.rental.car.dao.CarDao;
import com.online.rental.car.model.Car;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
@WebAppConfiguration
public class CarDaoTest {

	@Autowired
	private CarDao carDao;

	private byte[] bFile;

	@Before
	public void setUp() {
		// File file = new File("images\\extjsfirstlook.jpg"); //windows

		File file = new File("A:/myworkspae/car4.png");

		bFile = new byte[(int) file.length()];

		try {

			FileInputStream fileInputStream = new FileInputStream(file);

			fileInputStream.read(bFile);

			fileInputStream.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	@Ignore
	@Test
	public void carSave() {

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
		car.setImage(bFile);

		Car c = carDao.save(car);
		Assert.assertNotNull(c);
	}

	@Ignore
	@Test
	public void getCar() throws IOException {
		Car car = carDao.findOne(21993542L);

		FileOutputStream fos = new FileOutputStream("A:/myworkspae/car1-test.jpg");
		fos.write(car.getImage());
		fos.close();

	}
	@Ignore
	@Test
	public void updateCarImage() {
		long id = 3;
		Car c = carDao.findOne(id);
		c.setImage(bFile);
		
		carDao.save(c);
		
	}
}
