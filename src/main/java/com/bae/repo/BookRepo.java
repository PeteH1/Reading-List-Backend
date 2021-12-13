package com.bae.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bae.domain.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {

	List<Book> findByNameContainingIgnoreCase(String name);

	List<Book> findByGenreContainingIgnoreCase(String genre);

	List<Book> findByIsFiction(Boolean bool);

}
