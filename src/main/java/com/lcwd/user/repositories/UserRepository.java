package com.lcwd.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.user.Entities.User;

public interface UserRepository extends JpaRepository<User, String> {
			
	
	
}
