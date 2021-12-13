package com.bae.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.domain.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:book-schema.sql",
		"classpath:book-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class BookControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Book testBook = new Book("Test Book 2", "Test Author 2", true, "Adventure", "12345");
		String testBookAsJSON = this.mapper.writeValueAsString(testBook);

		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testBookAsJSON);

		Book createdBook = new Book(2, "Test Book 2", "Test Author 2", true, "Adventure", "12345");
		String createdBookAsJSON = this.mapper.writeValueAsString(createdBook);

		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(createdBookAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetAll() throws Exception {
		Book savedBook = new Book(1, "Test Book", "Test Author", false, "Adventure", "123");
		String savedBookAsJSON = this.mapper.writeValueAsString(List.of(savedBook));

		RequestBuilder req = get("/getAll").contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedBookAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testGet() throws Exception {
		Book savedBook = new Book(1, "Test Book", "Test Author", false, "Adventure", "123");
		String savedBookAsJSON = this.mapper.writeValueAsString(savedBook);

		RequestBuilder req = get("/get/" + savedBook.getId()).contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedBookAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testFindByName() throws Exception {
		Book savedBook = new Book(1, "Test Book", "Test Author", false, "Adventure", "123");
		String savedBookAsJSON = this.mapper.writeValueAsString(List.of(savedBook));

		RequestBuilder req = get("/findByName/" + savedBook.getName()).contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedBookAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testFindByGenre() throws Exception {
		Book savedBook = new Book(1, "Test Book", "Test Author", false, "Adventure", "123");
		String savedBookAsJSON = this.mapper.writeValueAsString(List.of(savedBook));

		RequestBuilder req = get("/findByGenre/" + savedBook.getGenre()).contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedBookAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testFindByFiction() throws Exception {
		Book savedBook = new Book(1, "Test Book", "Test Author", false, "Adventure", "123");
		String savedBookAsJSON = this.mapper.writeValueAsString(List.of(savedBook));

		RequestBuilder req = get("/findByFiction/0").contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedBookAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testUpdate() throws Exception {
		Book savedBook = new Book(1, "Test Book", "Test Author", false, "Updated genre", "123");
		String savedBookAsJSON = this.mapper.writeValueAsString(savedBook);

		RequestBuilder req = put("/update/" + savedBook.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(savedBookAsJSON);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedBookAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testDelete() throws Exception {
		this.mvc.perform(delete("/delete/1")).andExpect(status().isNoContent());
	}

}
