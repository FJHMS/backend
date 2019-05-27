package com.itf.schulung.springboot.fullstack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itf.schulung.springboot.fullstack.model.Account;
import com.itf.schulung.springboot.fullstack.model.Transaction;
import com.itf.schulung.springboot.fullstack.model.User;
import com.itf.schulung.springboot.fullstack.repositorys.AccountRepository;
import com.itf.schulung.springboot.fullstack.repositorys.TransactionRepository;
import com.itf.schulung.springboot.fullstack.repositorys.UserRepository;

@RestController
public class MyRestController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private TransactionRepository transRepo;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "users")
	public List<User> index() {
		return userRepo.findAll();
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("users/{id}")
	public ResponseEntity<User> show(@PathVariable long id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "users/post", consumes = "application/json")
	public ResponseEntity<User> postUser(@RequestBody User receivedUser) {
		userRepo.save(receivedUser);
		return new ResponseEntity<User>(receivedUser, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping(value = "users/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			userRepo.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "accounts")
	public List<Account> all() {
		return accountRepo.findAll();
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("accounts/{id}")
	public ResponseEntity<Account> id(@PathVariable long id) {
		Optional<Account> user = accountRepo.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "accounts/post", consumes = "application/json")
	public ResponseEntity<Account> postAccount(@RequestBody Account receivedAccount) {
		accountRepo.save(receivedAccount);
		return new ResponseEntity<Account>(receivedAccount, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/transactions", consumes = "application/json")
	public ResponseEntity<Transaction> postTransaction(@RequestBody Transaction transaction) {
		Optional<Account> receiverAccount = accountRepo.findById(transaction.getReceiverId());
		Optional<Account> senderAccount = accountRepo.findById(transaction.getSenderId());
		
		if(!(receiverAccount.isPresent() && senderAccount.isPresent())) {
			return new ResponseEntity<Transaction>(transaction, HttpStatus.NOT_FOUND);
		} else {
			accountRepo.save(receiverAccount.get().deposit(transaction.getAmount()));
			accountRepo.save(senderAccount.get().withdraw(transaction.getAmount()));
			transRepo.save(transaction);
			return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
		}
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "transactions")
	public List<Transaction> allTransactions() {
		return transRepo.findAll();
	}
	
	
}
