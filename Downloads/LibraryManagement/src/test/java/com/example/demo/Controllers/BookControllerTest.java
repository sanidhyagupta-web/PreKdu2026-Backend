//package com.example.demo.Controllers;
//
//import com.example.demo.DTOs.BookDto;
//import com.example.demo.Models.Book;
//import com.example.demo.Service.BookService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(BookController.class)
//class BookControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockitoBean
//    private BookService bookService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void AddBookController() throws Exception{
//        Book book = new Book();
//        book.setBookId(1);
//        book.setTitle("Clean Code");
//        book.setAuthor("Robert C. Martin");
//        book.setPrice(450.0);
//        book.setRatings(9);
//        book.setPublishedDate(new Date());
//        book.setNumberOfPages(464);
//
//        Mockito.when(bookService.AddBook(Mockito.any(Book.class))).thenReturn(book);
//
//        mockMvc.perform(post("/api/Library/AddBook")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(book)))
//                .andExpect(status().isAccepted())
//                .andExpect(jsonPath("$.title").value("Clean Code"))
//                .andExpect(jsonPath("$.author").value("Robert C. Martin"))
//                .andExpect(jsonPath("$.price").value(450.0))
//                .andExpect(jsonPath("$.ratings").value(9))
//                .andExpect(jsonPath("$.numberOfPages").value(464));
//
//    }
//
//    @Test
//    void GetBookController() throws Exception{
//        BookDto book = new BookDto();
//        book.setBookId(2);
//        book.setTitle("Harry Potter");
//        book.setAuthor("JK Rowlings");
//        book.setPrice(400.50);
//
//        Mockito.when(bookService.GetBook(2)).thenReturn(book);
//
//        mockMvc.perform(get("/api/Library/GetBook/2")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(book)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("Harry Potter"))
//                .andExpect(jsonPath("$.author").value("JK Rowlings"))
//                .andExpect(jsonPath("$.price").value(400.50))
//                .andExpect(jsonPath("$.bookId").value(2));
//    }
//}