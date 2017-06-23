package com.online.rental.car.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email", schema = "rental")
public class EmailContent {
	
	@Id
    @Column(name = "id")
	private Long id;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "subject")
	private String subject;
	
	
	private String receiver;
	
	private String sender;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	
	
	
}
