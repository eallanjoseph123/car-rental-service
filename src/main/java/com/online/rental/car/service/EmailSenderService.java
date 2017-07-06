package com.online.rental.car.service;

import com.online.rental.car.model.EmailContent;

public interface EmailSenderService {
	
	/**
	 * Send the information regarding the car reservation number. Return true if successfully sent the email, false if failed to sent.
	 * 
	 * @param receiver
	 * @param reserveNumber
	 * @return
	 */
	public boolean sendEmail(final String receiver, final String reserveNumber);
	
	
	/**
	 * Send the information regarding the car reservation number. Return true if successfully sent the email, false if failed to sent.
	 * 
	 * @param content
	 * @return
	 */
	public boolean sendEmail(final EmailContent content);
}
