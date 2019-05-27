package com.itf.schulung.springboot.fullstack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	private double balance;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
	
	public Account() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Account(double balance, User user) {
		this.balance = balance;
		this.user = user;
	}
	
	public Account withdraw(double amount){
	      this.balance -= amount;
	      return this;
	    }
	
	public Account deposit(double amount){
	        this.balance += amount;
	        return this;
	    }

}
