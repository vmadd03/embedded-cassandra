package com.safeway.j4u.inmemorycassandra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safeway.j4u.inmemorycassandra.model.Book;
import com.safeway.j4u.inmemorycassandra.repository.BookRepository;

@RestController
@RequestMapping("/book")
public class BookController {
	
	private final BookRepository bookRepository;
	
	@Autowired
	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@RequestMapping(value = "/insertBook", produces = { "application/json" }, consumes = {"application/json" }, method = RequestMethod.POST) 
	public Book insertBook(@RequestBody Book book) {
		bookRepository.save(book.getTitle(), book.getPublisher());
		return book;
	}

	
}
					