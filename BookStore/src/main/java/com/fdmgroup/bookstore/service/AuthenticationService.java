package com.fdmgroup.bookstore.service;

import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.model.User;

public class AuthenticationService {
	
	private UserRepository userRepository;
	
	public AuthenticationService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
		
	public User authenticate(String username, String password) throws UserNotFoundExeception {
		
		User user = userRepository.findByUserName(username);
		if(user.getPassword() ==  password)
			return user;
		else 	
			throw new UserNotFoundExeception("User not found");
		
	}

}
