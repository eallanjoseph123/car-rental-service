package com.online.rental.car.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jvnet.mock_javamail.Mailbox;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.online.rental.car.ApplicationConfig;
import com.online.rental.car.dao.EmailDao;
import com.online.rental.car.model.Email;
import com.online.rental.car.model.EmailConfigProp;
import com.online.rental.car.model.EmailContent;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
@WebAppConfiguration
public class EmailServiceTest {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private EmailConfigProp emailPop;
	
	private Email email;
	
	private EmailContent content;
	
	@Mock
	private EmailDao emailDao;
	
	@Mock
	private EmailSenderService emailSenderService;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		email = new Email(javaMailSender);
		
		content = new EmailContent();
		content.setSender(emailPop.getUsername());
		content.setMessage("app test car");
		content.setSubject("TEST email");
		content.setReceiver("test@src.com");
		
		List<EmailContent> value = new ArrayList<>();
		value.add(content);
		Mockito.when(emailDao.findAll()).thenReturn(value);
	}
	
	
	//use this in integration testing
	@Ignore
	@Test
	public void emailSendTest()
	{
		
		content.setMessage("app test car");
		content.setSubject("TEST email");
		content.setReceiver("eallanjoseph@gmail.com");
		email.send(content);
	}
	
	//TODO: To be removed. This is the old way to send an email to clients
	@Ignore
	@Test
	public void emailSend2Test() throws MessagingException, IOException {

	
		List<EmailContent> contents = emailDao.findAll();
		EmailContent  content2 = contents.get(0);
		email.send(content2);

		List<Message> inbox = Mailbox.get(content.getReceiver());

		Assert.assertTrue(inbox.size() == 1);
		String sub = inbox.get(0).getSubject();
		String con2 = (String) inbox.get(0).getContent();
		Assert.assertEquals(content.getSubject(), sub);
		Assert.assertEquals(content.getMessage(), con2);
		
		Mockito.verify(emailDao,Mockito.atLeast(1)).findAll();
	}
	
	@Test
	public void emailSend3Test() throws MessagingException, IOException{
		
		List<EmailContent> contents = emailDao.findAll();
		EmailContent  message = contents.get(0);
		
		
		Mockito.when(emailSenderService.sendEmail(message)).thenReturn(Boolean.TRUE);
		boolean res = emailSenderService.sendEmail(message);
		email.send(message);
		List<Message> inbox = Mailbox.get(content.getReceiver());

		Assert.assertTrue(inbox.size() == 1);
		
		String sub = inbox.get(0).getSubject();
		String con2 = (String) inbox.get(0).getContent();
		
		Assert.assertEquals(content.getSubject(), sub);
		Assert.assertEquals(content.getMessage(), con2);
		
		Assert.assertTrue(res);
		
		Mockito.verify(emailDao,Mockito.atLeast(1)).findAll();
		
		Mockito.verify(emailSenderService,Mockito.atLeast(1)).sendEmail(message);
	}
	
	
	
}
