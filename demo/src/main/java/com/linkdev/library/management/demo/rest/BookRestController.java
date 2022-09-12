package com.linkdev.library.management.demo.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.linkdev.library.management.demo.entity.Book;
import com.linkdev.library.management.demo.service.BookService;

@RestController
@RequestMapping("/book")
public class BookRestController {
	@Autowired
	private BookService bookesrvice;

	@PostMapping("/add")
	public ResponseEntity<Void> addBook(@RequestBody Book theBook) {
		Book theBook_ = null;
		theBook_ = bookesrvice.save(theBook);

		if (theBook_ == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(theBook_.getId())
				.toUri();


		return ResponseEntity.created(location).build();

	}

	@PutMapping("/update")
	public ResponseEntity<Void> updateEmployee(@RequestBody Book theBook) {

		Book theBook_ = null;
		theBook_ = bookesrvice.save(theBook);
		if (theBook_ == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(theBook_.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping
	public List<Book> findAll() {
		return bookesrvice.findAll();
	}

	@GetMapping("/getById/{bookId}")
	public Book getEmployee(@PathVariable int bookId) {

		Book theBook = bookesrvice.findById(bookId);

		if (theBook == null) {
			throw new RuntimeException(" bookId id not found - " + bookId);
		}

		return theBook;
	}

	@DeleteMapping("/{bookId}")
	public String deleteEmployee(@PathVariable int bookId) {

		Book tempBook = bookesrvice.findById(bookId);

		if (tempBook == null) {
			throw new RuntimeException("Book id not found - " + bookId);
		}

		bookesrvice.deleteById(bookId);

		return "Deleted Book id - " + bookId;
	}
}
