package com.fdmgroup.bookstore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.bookstore.data.OrderRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;

public class OrderingService {
	
	private OrderRepository orderRepository;
	List<Order> listOfOrders;
	Order order;
	
	public OrderingService(OrderRepository orderRoppository) {
		this.orderRepository = orderRoppository;
		listOfOrders = new ArrayList<>();
		order = new Order();
	}
	
	public void placeOrder(Book book, User user) {
		
		LocalDateTime now = LocalDateTime.now();
		//order.setOrderDateTime(now); // temp dealy
		order.setBookOrdered(book);
		order.setUser(user);
		orderRepository.save(order);
	}
	
		
	public List<Order> getOrdersForUser(User user) throws OrderNotFoundException {
		listOfOrders = user.getOrders();
		if(listOfOrders.size() > 0)
			return listOfOrders;
		else 
			throw new OrderNotFoundException("No order was found for the given user.");
		
	}
	
	
	public List<Order> getOrdersForBook(Book book) throws OrderNotFoundException {
		
		listOfOrders = orderRepository.findAll();
		ArrayList<Order> result=new ArrayList<>();
		for(Order order: listOfOrders) {
		     if(order.getBookOrdered() == book) {
		          result.add(order);
		      }
		    }
		if(result.size() > 0)
		     return result;
		 else
		    throw new OrderNotFoundException("No order was found for the passed book.");	
	}

}
