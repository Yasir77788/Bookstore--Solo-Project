package com.fdmgroup.tdd.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.data.OrderRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;
import com.fdmgroup.bookstore.service.BookService;
import com.fdmgroup.bookstore.service.ItemNotFoundException;
import com.fdmgroup.bookstore.service.OrderNotFoundException;
import com.fdmgroup.bookstore.service.OrderingService;

public class OrderingServiceTest {
	
	// refactoring or generalization
	
	    OrderRepository orderRepositoryMock;
	    OrderingService orderingService;
		
		@BeforeEach
		public void init()
		{
			orderRepositoryMock = mock(OrderRepository.class);
			// constructor injection 
			orderingService = new OrderingService(orderRepositoryMock);
		}
		
		
		@Test
		public void test_placeOrder_void_method_takes_a_Book_and_a_User(){
			
			// Arrange
			Book book = new Book();
			User user = new User();
			Order expectedOrder = new Order();
			
			expectedOrder.setBookOrdered(book);
			expectedOrder.setUser(user);
			
			// act
			orderingService.placeOrder(book, user);
			
			verify(orderRepositoryMock, times(1)).save(expectedOrder);
			
		}
		
		@Test
		public void test_getOrdersForUser_method_takes_a_User_and_returns_list_of_orders_for_the_user() throws OrderNotFoundException{
			
			// Arrange
			User user1 = new User();
			
			Order order1 = new Order();
			Order order2 = new Order();
			
			List<Order> orderList = new ArrayList<>();
			orderList.add(order2);
			orderList.add(order2);
			user1.setOrders(orderList);
			
			List<Order> expectedistOfOrders = new ArrayList<>();
			expectedistOfOrders.add(order1);
			expectedistOfOrders.add(order2);
			
			List<Order> actualListOfOrders = new ArrayList<>();
			
			// act
			actualListOfOrders = orderingService.getOrdersForUser(user1);
			
			// assert
			assertEquals(expectedistOfOrders, actualListOfOrders);
			
		}

		
		@Test
		public void test_getOrdersForBook_method_takes_a_book_returns_a_list_of_orders_for_the_book() throws OrderNotFoundException{
			
			// Arrange
			Book book = new Book();
			
			Order order1 = new Order();
			Order order2 = new Order();
			order1.setBookOrdered(book);
			order2.setBookOrdered(book);
			
			List<Order> listOfAllOrders = new ArrayList<>();
			listOfAllOrders.add(new Order());
			listOfAllOrders.add(new Order());
			listOfAllOrders.add(order1);
			listOfAllOrders.add(order2);
			
			
			List<Order> expectedistOfOrders = new ArrayList<>();
			expectedistOfOrders.add(order1);
			expectedistOfOrders.add(order2);
			
			
			List<Order> actualListOfOrders = new ArrayList<>();
			when(orderRepositoryMock.findAll()).thenReturn(listOfAllOrders);
			
			// act
			actualListOfOrders = orderingService.getOrdersForBook(book);
			
			// assert
			assertEquals(expectedistOfOrders, actualListOfOrders);
			
		}

}
