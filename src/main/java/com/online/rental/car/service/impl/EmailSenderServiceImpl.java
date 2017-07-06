package com.online.rental.car.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.online.rental.car.dao.EmailDao;
import com.online.rental.car.model.Email;
import com.online.rental.car.model.EmailContent;
import com.online.rental.car.service.EmailSenderService;

@Service
public class EmailSenderServiceImpl implements EmailSenderService{
	
	@Autowired
	private EmailDao emailDao;
	
	private Email email;
	
	@Autowired
	public EmailSenderServiceImpl(JavaMailSender javaMailSender){
		email = new Email(javaMailSender);
	}

	
	
	@Override
	public boolean sendEmail(String receiver, String reserveNumber) {
		boolean result = true;		
		try{
			
			List<EmailContent> contents = emailDao.findAll();
			EmailContent content = (null == contents) ? new EmailContent() : contents.get(0);
			StringBuilder message = new StringBuilder(content.getMessage());
			message.append(" Reserved number for your Car :").append(reserveNumber);
			content.setMessage(message.toString());
			content.setReceiver(receiver);
			email.send(content);
			
		}catch(Exception e){
			result = false;
		}
		return result;
	}

	@Override
	public boolean sendEmail(EmailContent content) {
		// TODO Auto-generated method stub
		return false;
	}

}
