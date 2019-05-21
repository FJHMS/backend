package com.itf.schulung.springboot.fullstack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itf.schulung.springboot.fullstack.model.User;
import com.itf.schulung.springboot.fullstack.repositorys.UserRepository;

@RestController
public class MyRestController {
	
	@Autowired
	private UserRepository userRepo;

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
	@GetMapping(value = "users/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			userRepo.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
