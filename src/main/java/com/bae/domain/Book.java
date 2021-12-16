package com.bae.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String author;
	private Boolean isFiction;
	private String genre;
	private String isbn;

	public Book() {
	}

	public Book(String name, String author, Boolean isFiction, String genre) {
		this.name = name;
		this.author = author;
		this.isFiction = isFiction;
		this.genre = genre;
	}

	public Book(String name, String author, Boolean isFiction, String genre, String isbn) {
		this.name = name;
		this.author = author;
		this.isFiction = isFiction;
		this.genre = genre;
		this.isbn = isbn;
	}

	public Book(Integer id, String name, String author, Boolean isFiction, String genre, String isbn) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.isFiction = isFiction;
		this.genre = genre;
		this.isbn = isbn;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Boolean getIsFiction() {
		return isFiction;
	}

	public void setIsFiction(Boolean isFiction) {
		this.isFiction = isFiction;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(genre, other.genre)
				&& Objects.equals(id, other.id) && Objects.equals(isFiction, other.isFiction)
				&& Objects.equals(isbn, other.isbn) && Objects.equals(name, other.name);
	}

}
