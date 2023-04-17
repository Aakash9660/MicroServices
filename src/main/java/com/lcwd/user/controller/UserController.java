package com.lcwd.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.Entities.User;
import com.lcwd.user.Services.UserService;

import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	//create
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	
	// single user get
	@GetMapping("/{userId}")
	@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId)
	{
		
		User user2 = userService.getUser(userId);
		return ResponseEntity.ok(user2);
	}
	
	// creating fallback method for cuircuit breaker
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex)
	{
		
//		Logger.info("fallback is executed becuase service is down : ",ex.getMessage());
		User user = User.builder()
			.email("dummy@gmail.com")
			.name("dummy")
			.about("this user is dummy because service is down")
			.build();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	
	// all user get
	@GetMapping
	public ResponseEntity<List<User>> getAllUser()
	{
		List<User> alluser = userService.getAllUser();
		return ResponseEntity.ok(alluser);
	}
}
