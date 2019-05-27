package com.itf.schulung.springboot.fullstack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	private long receiverId;
	
	private long senderId;
	private double amount;
	
	public Transaction() {
		
	}
	
	public Transaction(long senderId, double amount, long receiverId) {
		this.receiverId = receiverId;
		this.senderId = senderId;
		this.amount = amount;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}
	public long getSenderId() {
		return senderId;
	}
	public void setSender(long senderId) {
		this.senderId = senderId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
