package com.linkdev.library.management.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkdev.library.management.demo.entity.Book;
import com.linkdev.library.management.demo.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book findById(int theId) {
		Optional<Book> result = bookRepository.findById(theId);

		Book theBook = null;

		if (result.isPresent()) {
			theBook = result.get();
		} else {
			throw new RuntimeException("Did not find employee id - " + theId);
		}

		return theBook;
	}

	
	public Book save(Book theBook) {
		return bookRepository.save(theBook);
	}


	public String  deleteById(int theId) {
		bookRepository.deleteById(theId);
		return "Deleted Book id - " + theId;
	}
}
