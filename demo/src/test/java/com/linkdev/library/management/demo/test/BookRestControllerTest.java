package com.linkdev.library.management.demo.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.linkdev.library.management.demo.entity.Book;
import com.linkdev.library.management.demo.rest.BookRestController;
import com.linkdev.library.management.demo.service.BookService;

@ExtendWith(SpringExtension.class)
@WithMockUser
@WebMvcTest(value = BookRestController.class)
class BookRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@Test
	public void getBookById() throws Exception {
		System.out.println("---->> Find");
		Book mockBook = new Book(3, "Book16", "Heba-Abdelwanees");
		Mockito.when(bookService.findById(Mockito.anyInt())).thenReturn(mockBook);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book/getById/3")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println("Result --- >> " + result.getResponse().getContentAsString());

		String expected = "{\"id\":3,\"title\":\"Book16\",\"author\":\"Heba-Abdelwanees\"}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void addBook() throws Exception {
		System.out.println("---->> Create");
		Book mockBook = new Book(11, "Book33", "salma");
		Mockito.when(bookService.save(Mockito.any(Book.class))).thenReturn(mockBook);
		String bookJsonToPost = "{\"title\":\"Book33\",\"author\":\"salma\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/book/add").accept(MediaType.APPLICATION_JSON)
				.content(bookJsonToPost).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		System.out.println("HttpStatus.CREATED.value()--- >> " + HttpStatus.CREATED.value());
		System.out.println("response.getStatus() --- >> " + response.getStatus());
		System.out.println("response :: LOCATION --- >> " + response.getHeader(HttpHeaders.LOCATION));

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	@Test
	public void updateBook() throws Exception {

		System.out.println("---->> Update");
		Book mockBook = new Book(7, "updatedBook", "heba");
		Mockito.when(bookService.save(Mockito.any(Book.class))).thenReturn(mockBook);
		String bookJsonToUpdate = "{\"title\":\"Book33\",\"author\":\"salma\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/book/update").accept(MediaType.APPLICATION_JSON)
				.content(bookJsonToUpdate).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		System.out.println("HttpStatus.Updated.value()--- >> " + HttpStatus.CREATED.value());
		System.out.println("response.getStatus() --- >> " + response.getStatus());
		System.out.println("response :: LOCATION --- >> " + response.getHeader(HttpHeaders.LOCATION));

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	@Test
	public void deleteBook() throws Exception {
		System.out.println("---->> Delete");
	     Mockito.when(bookService.deleteById(10)).thenReturn("Deleted Book id - 10");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/book/10");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		System.out.println("Response -->> "+response.toString());
		System.out.println(response.getStatus());
		String deletedMessage = response.getContentAsString();
		System.out.println("deletedMessage--- >> " + deletedMessage);
		assertEquals(deletedMessage, "Deleted Book id - 10");
	}
 
}
