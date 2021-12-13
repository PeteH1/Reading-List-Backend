package com.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bae.domain.Book;
import com.bae.repo.BookRepo;

@Service
public class BookService {

	private BookRepo repo;

	@Autowired
	public BookService(BookRepo repo) {
		super();
		this.repo = repo;
	}

	public Book createBook(Book book) {
		Book created = this.repo.save(book);
		return created;
	}

	public List<Book> getAllBooks() {
		return this.repo.findAll();
	}

	public Book getBook(Integer id) {
		return this.repo.findById(id).get();
	}

	public List<Book> findByName(String name) {
		return this.repo.findByNameContainingIgnoreCase(name);
	}

	public List<Book> findByGenre(String genre) {
		return this.repo.findByGenreContainingIgnoreCase(genre);
	}

	public List<Book> findByIsFiction(Boolean bool) {
		return this.repo.findByIsFiction(bool);
	}

	public Book updateBook(Integer id, Book newBook) {
		Book existing = this.repo.findById(id).get();

		existing.setName(newBook.getName());
		existing.setAuthor(newBook.getAuthor());
		existing.setIsFiction(newBook.getIsFiction());
		existing.setGenre(newBook.getGenre());
		existing.setIsbn(newBook.getIsbn());

		return this.repo.save(existing);
	}

	public void deleteBook(Integer id) {
		this.repo.deleteById(id);
	}

}
