package com.fdmgroup.bookstore.data;

import java.util.List;

import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.Order;

public interface OrderRepository {
	public void save(Order order);
	List<Order> findAll();

}
