package com.bae.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.domain.Book;
import com.bae.service.BookService;

@RestController
public class BookController {

	private BookService service;

	@Autowired
	public BookController(BookService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		Book created = this.service.createBook(book);
		ResponseEntity<Book> response = new ResponseEntity<Book>(created, HttpStatus.CREATED);
		return response;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Book>> getAllBooks() {
		return ResponseEntity.ok(this.service.getAllBooks());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Book> getBook(@PathVariable Integer id) {
		return ResponseEntity.ok(this.service.getBook(id));
	}

	@GetMapping("/findByName/{name}")
	public ResponseEntity<List<Book>> findByName(@PathVariable String name) {
		return ResponseEntity.ok(this.service.findByName(name));
	}

	@GetMapping("/findByGenre/{genre}")
	public ResponseEntity<List<Book>> findByGenre(@PathVariable String genre) {
		return ResponseEntity.ok(this.service.findByGenre(genre));
	}

	@GetMapping("/findByFiction/{bool}")
	public ResponseEntity<List<Book>> findByFiction(@PathVariable Boolean bool) {
		return ResponseEntity.ok(this.service.findByIsFiction(bool));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book newBook) {
		Book updated = this.service.updateBook(id, newBook);
		ResponseEntity<Book> response = new ResponseEntity<Book>(updated, HttpStatus.ACCEPTED);
		return response;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
		this.service.deleteBook(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
