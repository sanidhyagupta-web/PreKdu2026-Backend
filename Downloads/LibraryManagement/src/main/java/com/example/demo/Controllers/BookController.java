package com.example.demo.Controllers;

import com.example.demo.Models.Book;
import com.example.demo.Service.BookService;
import com.example.demo.Utilities.BookResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/api/v1/Library")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RestClient restClient;

    @PostMapping // Adding a new book
    @Operation(summary = "Starts the async book processing")
    public ResponseEntity<Book> AddBook(@Valid @RequestBody Book book) {
        return new ResponseEntity<Book>(bookService.AddBook(book), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> GetBook(@PathVariable("id") Long id) {
        Book book = bookService.GetBook(id);
        EntityModel<Book> bookModel = EntityModel.of(book,
                linkTo(methodOn(BookController.class).GetBook(id)).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks(0, 3, "title","asc")).withRel("all-books")
        );
        return ResponseEntity.ok(bookModel);
    }

    @Operation(summary = "View All Books" ,
               description = "The method will fetch the details of all the books")
    @Tag(name = "Get")
    @ApiResponse(responseCode = "200" , description = "Fetched all the books")
    @GetMapping("/books")
    public ResponseEntity<BookResponse> getAllBooks(
            @RequestParam(value = "PageNumber" , defaultValue = "0" , required = false) Integer PageNumber ,
            @RequestParam(value = "PageSize" , defaultValue = "5" , required = false) Integer PageSize,
            @RequestParam(value = "SortBy" , defaultValue = "bookId" , required = false) String sortBy ,
            @RequestParam(value = "SortDir" , defaultValue = "asc" , required = false) String sortDir
            ) {
                BookResponse bookPage = bookService.findAllBooks(PageSize,PageNumber,sortBy,sortDir);
                return new ResponseEntity<>(bookPage,HttpStatus.OK);
    }


    @PutMapping("/{id}") // Updating the book
    public ResponseEntity<Book> UpdateBook(@PathVariable("id") Long id, @Valid @RequestBody Book book){
        return new ResponseEntity<Book>(bookService.UpdateBook(id,book),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteBook(@PathVariable("id") Long id){
        System.out.println("Running By Thread : " + Thread.currentThread().getName());
        bookService.DeleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("Author/{author}")
    public ResponseEntity<List<Book>> GetBookByAuthor(@PathVariable("author") String author){
        return new ResponseEntity<List<Book>>(bookService.searchBookByAuthor(author), HttpStatus.OK);
    }

    @GetMapping("analytics/audit")
    public ResponseEntity<Map<String,Long>> getAnalytics(){
        return new ResponseEntity<>(bookService.getAnalytics(),HttpStatus.OK);
    }
}