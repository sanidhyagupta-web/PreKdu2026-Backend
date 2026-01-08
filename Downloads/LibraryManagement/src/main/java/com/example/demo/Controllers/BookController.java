package com.example.demo.Controllers;

import com.example.demo.Models.Book;
import com.example.demo.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/api/v1/Library")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping // Adding a new book
    public ResponseEntity<Book> AddBook(@Valid @RequestBody Book book){
        return new ResponseEntity<Book>(bookService.AddBook(book),HttpStatus.ACCEPTED);
    }

    @GetMapping("/Id/{id}")
    public ResponseEntity<EntityModel<Book>> GetBook(@PathVariable("id") Long id){
        Book book = bookService.GetBook(id);
        EntityModel<Book> bookModel = EntityModel.of(book,
                linkTo(methodOn(BookController.class).GetBook(id)).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks(null, "asc", 0)).withRel("all-books")
        );
        return ResponseEntity.ok(bookModel);
    }

    @GetMapping("/books")
    public ResponseEntity<Page<Book>> getAllBooks(
            @RequestParam(required = false) String author,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "0") int page) {

        return ResponseEntity.ok(
                bookService.GetAllBooks(author, sort, page)
        );
    }


    @PutMapping("/Id/{id}") // Updating the book
    public ResponseEntity<Book> UpdateBook(@PathVariable("id") Long id, @Valid @RequestBody Book book){
        return new ResponseEntity<Book>(bookService.UpdateBook(id,book),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/Id/{id}")
    public ResponseEntity<Void> DeleteBook(@PathVariable("id") Long id){
        bookService.DeleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/Author/{author}")
    public ResponseEntity<List<Book>> GetBookByAuthor(@PathVariable("author") String author){
        return new ResponseEntity<List<Book>>(bookService.searchBookByAuthor(author), HttpStatus.OK);
    }
}