package com.fdmgroup.tdd.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.model.User;
import com.fdmgroup.bookstore.service.AuthenticationService;
import com.fdmgroup.bookstore.service.UserNotFoundExeception;

public class AuthenticationServiceTest {
	
	   // refactoring or generalization
		UserRepository userRepositoryMock;
		AuthenticationService authenticationService;
		
		@BeforeEach
		public void init()
		{
			userRepositoryMock = mock(UserRepository.class);
			// constructor injection 
			authenticationService = new AuthenticationService(userRepositoryMock);
		}
		
		
		@Test
		public void test_authenticate_method_takes_a_username_returns_a_User() throws UserNotFoundExeception {
			
			// Arrange
			User expectedUser = new User();
			String username = "sam";
			String password = "ood3";
			expectedUser.setUsername(username);
			expectedUser.setPassword(password);
			
			// stubbing
			when(userRepositoryMock.findByUserName(username)).thenReturn(expectedUser); 

			// Act
			User actualUser = authenticationService.authenticate(username, password);
			
			
			verify(userRepositoryMock, times(1)).findByUserName(username);
			
			// Assert
			assertEquals(expectedUser, actualUser);
			
		}

}
