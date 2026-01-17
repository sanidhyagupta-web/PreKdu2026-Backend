//package com.example.demo.Service;
//
//import com.example.demo.DTOs.BookDto;
//import com.example.demo.Exception.DuplicateBookException;
//import com.example.demo.Exception.ResourceNotFoundException;
//import com.example.demo.Models.Book;
//import com.example.demo.Repository.BookRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.Optional;
//
//@ExtendWith(MockitoExtension.class)
//public class BookServiceTest {
//    @Mock
//    private BookRepository bookRepository;
//
//    @Mock
//    private Conversion conversion;
//
//    @InjectMocks
//    private BookService bookService;
//
//    // AAA Pattern
//
//    @Test
//    void TestGetBookWithoutException(){
//        // Arrange
//        Book book = new Book();
//        book.setBookId(1);
//
//        BookDto bookDto = new BookDto();
//        bookDto.setBookId(1);
//
//        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(book));
//        Mockito.when(conversion.ConvertToBook(book)).thenReturn(bookDto);
//
//        // Act
//        BookDto bookDto1 = bookService.GetBook(1);
//
//        // Assert
//        assertEquals(1,bookDto1.getBookId());// Asserting that the same bookId we get that we wanted
//        Mockito.verify(bookRepository,Mockito.times(1)).findById(1);// Verifying that bookrepository.findById(1) should be called only once.
//        Mockito.verify(conversion,Mockito.times(1)).ConvertToBook(book);// Verifying that conversion.ConvertToBook() should run only once.
//    }
//
//    @Test
//    void TestGetBookWithException(){
//        Mockito.when(bookRepository.findById(10)).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class,()->bookService.GetBook(10));// Asserting that the valid exception is thrown
//
//        Mockito.verify(bookRepository,Mockito.times(1)).findById(10);// Verifying that bookrepository.findById(1) should be called only once.
//        Mockito.verifyNoInteractions(conversion); // Verifying that conversion should not be called .
//    }
//
//    @Test
//    void TestAddBookWithoutException(){
//        Book book = new Book();
//        book.setBookId(2);
//        book.setAuthor("ABC");
//        book.setTitle("XYZ");
//
//        Mockito.when(bookRepository.save(book)).thenReturn(book);
//
//        Book book1 = bookService.AddBook(book);
//
//        assertEquals(book.getBookId(),book1.getBookId());
//        Mockito.verify(bookRepository,Mockito.times(1)).save(book);
//    }
//
//    @Test
//    void TestAddBookWithException(){
//        Book book = new Book();
//        book.setBookId(2);
//        book.setAuthor("ABC");
//        book.setTitle("XYZ");
//
//        Mockito.when(bookRepository.existsByAuthor("ABC")).thenReturn(true);
//        Mockito.when(bookRepository.existsByTitle("XYZ")).thenReturn(true);
//
//        assertThrows(DuplicateBookException.class,()->bookService.AddBook(book));
//    }
//
//    @Test
//    void TestUpdateBookWithoutException(){
//        // Arrange
//        Book existing = new Book();
//        existing.setBookId(34);
//        existing.setAuthor("Old");
//        existing.setPrice(20.00);
//        existing.setTitle("Old Title");
//
//        Book updated = new Book();
//        updated.setBookId(34);
//        updated.setAuthor("New");
//        updated.setPrice(40.00);
//        updated.setTitle("New Title");
//
//        Mockito.when(bookRepository.findById(34)).thenReturn(Optional.of(existing));
//        Mockito.when(bookRepository.save(existing)).thenReturn(existing);
//
//        //Act
//        Book result = bookService.UpdateBook(updated);
//
//        //Assert
//        assertEquals(34,result.getBookId());
//        Mockito.verify(bookRepository,Mockito.times(1)).findById(34);
//    }
//
//    @Test
//    void TestUpdateBookWithException(){
//        Book updated = new Book();
//        updated.setBookId(10);
//        Mockito.when(bookRepository.findById(10)).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class,()->bookService.UpdateBook(updated));
//        Mockito.verify(bookRepository,Mockito.times(1)).findById(10);
//    }
//
//    @Test
//    void TestDeleteBookWithoutException(){
//        Book book = new Book();
//        book.setBookId(3);
//
//        Mockito.when(bookRepository.findById(3)).thenReturn(Optional.of(book));
//
//        Book result = bookService.DeleteBook(3);
//
//        assertEquals(3,result.getBookId());
//        Mockito.verify(bookRepository,Mockito.times(1)).findById(3);
//        Mockito.verify(bookRepository,Mockito.times(1)).delete(book);
//    }
//
//    @Test
//    void TestDeleteBookWithException(){
//        Book book = new Book();
//        book.setBookId(99);
//
//        Mockito.when(bookRepository.findById(99)).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class,()->bookService.DeleteBook(99));
//        Mockito.verify(bookRepository,Mockito.times(1)).findById(99);
//        Mockito.verify(bookRepository,Mockito.times(0)).delete(book);
//    }
//}
