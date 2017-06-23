package com.online.rental.car.model;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;


public class Email {
	private JavaMailSender javaMailSender;

	public Email(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void send(final EmailContent content) throws MailException {
		validateContent(content);
			MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			
			messageHelper.setFrom(content.getSender());
			messageHelper.setTo(content.getReceiver());
			messageHelper.setSubject(content.getSubject());
			messageHelper.setText(content.getMessage());
		};

		javaMailSender.send(messagePreparator);

	}
	//TODO: Make a new validation for this function
	public void validateContent(EmailContent content) throws RuntimeException{
		if(content == null || content.getMessage().isEmpty() || content.getReceiver().isEmpty() || content.getSender().isEmpty()){
			
				throw new RuntimeException("Email Content has error");
			
		}
	}
}
