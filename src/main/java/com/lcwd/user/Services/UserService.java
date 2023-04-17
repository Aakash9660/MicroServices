  package com.lcwd.user.Services;

import java.util.List;

import com.lcwd.user.Entities.User;

public interface UserService {

	
	              // User operations
	
	//create
	User saveUser(User user);
	
	
	// get all users
	List<User> getAllUser();
	
	// get single user of given userId
	User getUser(String userId);
	
	// delete user by userId
	void deleteUser(String userId);
	
	//update User of particulatr Id
	User updateUser(String id);
	
}
