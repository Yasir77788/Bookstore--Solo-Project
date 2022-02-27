package com.fdmgroup.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;

public class BookService {
	
	private BookRepository bookRepository;
	List<Book> listOfBooks;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
		listOfBooks = new ArrayList<>();
	}
	
	public List<Book> getAllBooks() throws ItemNotFoundException{
		listOfBooks = bookRepository.findAll();
		if (listOfBooks.size() > 0)
			return listOfBooks;
		else
			throw new ItemNotFoundException("No book found.");
	}
	
	public List<Book> getBooksOfGenre(BookGenre bookGenre) throws ItemNotFoundException{
		
		listOfBooks = bookRepository.findAll();
		 ArrayList<Book> result=new ArrayList<>();
		    for(Book b: listOfBooks) {
		        if(b.getBookGenre() == bookGenre) {
		            result.add(b);
		        }
		    }
		    if(result.size() > 0)
		    	return result;
		    else
		    	throw new ItemNotFoundException("BookGenre not found.");
			
	}
	
	public List<Book> searchBooksByTitle(String title) throws ItemNotFoundException{
		
		 listOfBooks = bookRepository.findAll();
		 ArrayList<Book> result=new ArrayList<>();
		    for(Book b: listOfBooks) {
		        if(b.getTitle() == title) {
		            result.add(b);
		        }
		    }
		    if(result.size() > 0)
		    	return result;
		    else
		    	throw new ItemNotFoundException("Book title not found.");
	}
	
	public List<Book> searchBooksByAuthorName(String bookAuthorNameToSearch) throws ItemNotFoundException{
		
		listOfBooks = bookRepository.findAll();
		 ArrayList<Book> result=new ArrayList<>();
		    for(Book b: listOfBooks) {
		        if(b.getAuthor() == bookAuthorNameToSearch) {
		            result.add(b);
		        }
		    }
		    if(result.size() > 0)
		    	return result;
		    else
		    	throw new ItemNotFoundException("Book with author name  not found.");
	}
		

}
