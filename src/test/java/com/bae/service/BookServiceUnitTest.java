package com.bae.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.bae.domain.Book;
import com.bae.repo.BookRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BookServiceUnitTest {

	@Autowired
	private BookService service;

	@MockBean
	private BookRepo repo;

	/*
	 * These tests aren't doing a lot, but they're just an example of unit testing
	 * with Mockito in Spring
	 */

	@Test
	void testUpdateBook() {
		final Integer id = 1;
		Book oldBook = new Book(1, "Test", "Test Author", true, "Test Genre", "101");

		Book newBook = new Book(1, "New Name", "New Author", true, "Test Genre", "101");

		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(oldBook));
		Mockito.when(this.repo.save(newBook)).thenReturn(newBook);

		assertEquals(newBook, this.service.updateBook(oldBook.getId(), newBook));

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(newBook);
	}

	@Test
	void testCreateBook() {
		Book book = new Book(1, "Test", "Test Author", true, "Test Genre", "101");

		Mockito.when(this.repo.save(book)).thenReturn(book);

		assertEquals(book, this.service.createBook(book));

		Mockito.verify(this.repo, Mockito.times(1)).save(book);
	}

	@Test
	void testGetAllBooks() {
		Book bookToGet = new Book(1, "Test", "Test Author", false, "Test Genre", "101");
		List<Book> listToGet = new ArrayList<>(List.of(bookToGet));

		Mockito.when(this.repo.findAll()).thenReturn(listToGet);

		assertEquals(listToGet, this.service.getAllBooks());

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testGetBook() {
		final Integer id = 1;
		Book bookToGet = new Book(id, "Test", "Test Author", false, "Test Genre", "101");

		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(bookToGet));

		assertEquals(bookToGet, this.service.getBook(id));

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

	@Test
	void testFindByName() {
		final String name = "Test name";
		Book bookToGet = new Book(1, "Test name", "Test Author", false, "Test Genre", "101");
		List<Book> listToGet = new ArrayList<>(List.of(bookToGet));

		Mockito.when(this.repo.findByNameContainingIgnoreCase(name)).thenReturn(listToGet);

		assertEquals(listToGet, this.service.findByName(name));

		Mockito.verify(this.repo, Mockito.times(1)).findByNameContainingIgnoreCase(name);
	}

	@Test
	void testFindByGenre() {
		final String genre = "Test genre";
		Book bookToGet = new Book(1, "Test name", "Test Author", false, "Test Genre", "101");
		List<Book> listToGet = new ArrayList<>(List.of(bookToGet));

		Mockito.when(this.repo.findByGenreContainingIgnoreCase(genre)).thenReturn(listToGet);

		assertEquals(listToGet, this.service.findByGenre(genre));
		Mockito.verify(this.repo, Mockito.times(1)).findByGenreContainingIgnoreCase(genre);
	}

	@Test
	void testFindByIsFiction() {
		final Boolean bool = true;
		Book bookToGet = new Book(1, "Test name", "Test Author", true, "Test Genre", "101");
		List<Book> listToGet = new ArrayList<>(List.of(bookToGet));

		Mockito.when(this.repo.findByIsFiction(bool)).thenReturn(listToGet);

		assertEquals(listToGet, this.service.findByIsFiction(bool));
		Mockito.verify(this.repo, Mockito.times(1)).findByIsFiction(bool);
	}

}
