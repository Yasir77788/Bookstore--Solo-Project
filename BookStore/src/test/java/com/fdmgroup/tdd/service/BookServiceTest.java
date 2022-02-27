package com.fdmgroup.tdd.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;
import com.fdmgroup.bookstore.model.User;
import com.fdmgroup.bookstore.service.AuthenticationService;
import com.fdmgroup.bookstore.service.BookService;
import com.fdmgroup.bookstore.service.ItemNotFoundException;
import com.fdmgroup.bookstore.service.UserNotFoundExeception;

public class BookServiceTest {
	
	
	// refactoring 
	BookRepository bookRepositoryMock;
	BookService bookService;
	
	@BeforeEach
	public void init()
	{
		bookRepositoryMock = mock(BookRepository.class);
		// constructor injection 
		bookService = new BookService(bookRepositoryMock);
	}
	
	
	@Test
	public void test_getAllBooks_method_returns_a_list_of_Books() throws ItemNotFoundException{
		
		// Arrange
		List<Book> expectedListOfBooks  = new ArrayList<>();
		for(int i = 0; i < 5; i++)
			expectedListOfBooks.add(new Book());
		
		List<Book> actualListOfBooks  = new ArrayList<>();
	
		// stubbing 
		when(bookRepositoryMock.findAll()).thenReturn(expectedListOfBooks); 

		// Act
		actualListOfBooks = bookService.getAllBooks();
	
		// Assert
		assertEquals(expectedListOfBooks, actualListOfBooks);
		
	}
	
	
	@Test
	public void test_getBooksOfGenre_method_takes_a_BookGenre_returns_a_list_of_books_of_that_genre() throws ItemNotFoundException{
			
			BookGenre bookgenre = BookGenre.FICTION;
			// Arrange
			Book book1 = new Book();
			Book book2 = new Book();
			book1.setBookGenre(bookgenre);
			book2.setBookGenre(bookgenre);
			
			List<Book> listOfAllBooks  = new ArrayList<>();
			listOfAllBooks.add(new Book()); // non fiction
			listOfAllBooks.add(new Book()); // non fiction
			listOfAllBooks.add(new Book()); // non fiction
			listOfAllBooks.add(book1); // fiction
			listOfAllBooks.add(book2); // fiction
			
			List<Book> expectedListOfBooks  = new ArrayList<>();
			expectedListOfBooks.add(book1);
			expectedListOfBooks.add(book2);
		
			
			List<Book> actualListOfBooks  = new ArrayList<>();
		
			// stubbing 
			when(bookRepositoryMock.findAll()).thenReturn(listOfAllBooks); 

			// Act
			actualListOfBooks = bookService.getBooksOfGenre(bookgenre);
		
			// Assert
			assertEquals(expectedListOfBooks, actualListOfBooks);
			
		}


	
		@Test
		public void test_searchBooksByTitle_method_takes_a_book_title_returns_a_list_of_books_matching_the_title() throws ItemNotFoundException{
			
			// Arrange
			Book book1 = new Book();
			book1.setTitle("Java How to Program");
			Book book2 = new Book();
			book2.setTitle("Java How to Program");
			
			String bookTitle = "Java How to Program";
			
			List<Book> listOfBooks = new ArrayList<>();
			listOfBooks.add(new Book());
			listOfBooks.add(book1);
			listOfBooks.add(book2);
			listOfBooks.add(new Book());	
			
			List<Book> expectedListOfBooks  = new ArrayList<>();
			expectedListOfBooks.add(book1);
			expectedListOfBooks.add(book2);

			List<Book> actualListOfBooks  = new ArrayList<>();
			
			// stubbing - rigging or faking the object
			when(bookRepositoryMock.findAll()).thenReturn(listOfBooks);
		
		    // Act
			actualListOfBooks = bookService.searchBooksByTitle(bookTitle);
		
			// Assert
			assertEquals(expectedListOfBooks, actualListOfBooks);
			
		}
		
		
		@Test
		public void test_searchBooksByAuthorName_method_returns_a_list_of_books_matching_the_passed_author_name() throws ItemNotFoundException{
					
					// Arrange
					Book book1 = new Book();
					book1.setAuthor("Sam Adams");
					Book book2 = new Book();
					book2.setAuthor("Mo Ibrahim");
					Book book3 = new Book();
					book3.setAuthor("Mo Ibrahim");
					
					String authorName = "Mo Ibrahim";
					
					List<Book> listOfBooks  = new ArrayList<>();
					listOfBooks.add(book1);
					listOfBooks.add(book2);
					listOfBooks.add(book3);
					
					List<Book> expectedListOfBooks  = new ArrayList<>();
					expectedListOfBooks.add(book2);
					expectedListOfBooks.add(book3);

					List<Book> actualListOfBooks  = new ArrayList<>();
					
					// stubbing - 
					when(bookRepositoryMock.findAll()).thenReturn(listOfBooks);
				
				    // Act
					actualListOfBooks = bookService.searchBooksByAuthorName(authorName);
				
					// Assert
					assertEquals(expectedListOfBooks, actualListOfBooks);
					
				}


}
