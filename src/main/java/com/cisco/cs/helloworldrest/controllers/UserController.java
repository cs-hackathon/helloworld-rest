package com.cisco.cs.helloworldrest.controllers;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cisco.cs.helloworldrest.models.User;
import com.cisco.cs.helloworldrest.services.UserRepository;

@RestController
public class UserController {

	private static Logger log = Logger.getLogger(UserController.class.toString());

	@Autowired
	UserRepository userRepo;

	@RequestMapping(method = RequestMethod.POST, value = "/users")
	public ResponseEntity<User> create(@RequestBody User user) {
		log.info("UserController::create");
		ResponseEntity<User> response = null;
		if (userRepo.count() > 20) {
			response = ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body(null);
		} else {
			User savedUser = userRepo.save(user);
			response = ResponseEntity.ok().body(savedUser);
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/users/{userId}")
	public User update(@RequestBody User user, @PathVariable String userId) {
		log.info("UserController::update - " + userId);
		user.setId(userId);
		User savedUser = userRepo.save(user);
		return savedUser;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
	public User getById(@PathVariable String userId) {
		log.info("UserController::getById - " + userId);
		Optional<User> user = userRepo.findById(userId);
		return user.orElse(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users")
	public Iterable<User> getAll() {
		log.info("UserController::getAll");
		return userRepo.findAll();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
	public void deleteById(@PathVariable String userId) {
		log.info("UserController::delete - " + userId);
		userRepo.deleteById(userId);
	}

}
