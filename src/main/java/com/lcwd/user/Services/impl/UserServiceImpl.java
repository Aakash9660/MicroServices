package com.lcwd.user.Services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.Entities.Hotel;
import com.lcwd.user.Entities.Rating;
import com.lcwd.user.Entities.User;
import com.lcwd.user.Services.UserService;
import com.lcwd.user.exceptions.ResourceNotFoundException;
import com.lcwd.user.external.service.HotelService;
import com.lcwd.user.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;

	private Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {

		// generate unique userid
		String randomUserId = UUID.randomUUID().toString();

		user.setUser_Id(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	// get single user
	@Override
	public User getUser(String userId) {

		// get user from database with the help of userrepository
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with this Id is not present in server...!!"));

		// fetching rating of the above user from RATING SERVICE
		// localhost:8082/ratings/users/bf11a855-e1b2-4033-80e8-7c6247e005af
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUser_Id(), Rating[].class);
		
		logger.info("{} ", ratingsOfUser);
		
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		
		List<Rating> ratingList = ratings.stream().map(rating->{
			
			//api call to hotel service to get the hotel
			//localhost:8081/hotels/0447b61e-68f8-4cd1-9d9e-b714c7e5b7cb
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel = forEntity.getBody();
			
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			
//			logger.info("response status code : {}",forEntity.getStatusCode());
			
			
			//set the hotel to rating
			rating.setHotel(hotel);
			
			// return the rating
			return rating;
			
		}).collect(Collectors.toList());
		
		
		user.setRatings(ratingList);
		

		return user;

	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public User updateUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
