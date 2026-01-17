package com.example.demo.Service;

import com.example.demo.Exception.DuplicateBookException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Models.Book;
import com.example.demo.Repository.BookRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    public Book GetBook(Long BookId){
        Book book = bookRepository.findById(BookId).orElseThrow(()->new ResourceNotFoundException("Book","BookId", Math.toIntExact(BookId)));

        String coverImage = BookCoverClient.fetchCoverImage(book.getIsbn());
        book.setImageCoverURL(coverImage);

        return book;
    }

    public Page<Book> GetAllBooks(String author, String sort, int page){

        if(page<0){
            //
        }

        Sort sorting = sort.equalsIgnoreCase("desc")
                ? Sort.by("title").descending()
                : Sort.by("title").ascending();

        Pageable pageable = PageRequest.of(page, 10, sorting);

        if (author != null && !author.isBlank()) {
            return bookRepository.findByAuthorIgnoreCase(author, pageable);
        }

        return bookRepository.findAll(pageable);

    }

    public Book AddBook(@Valid Book book)  {
        logger.info("Adding new book: {} by {}", book.getTitle(), book.getAuthor());

        if(bookRepository.existsByTitle(book.getTitle()) && bookRepository.existsByAuthor(book.getAuthor())){
            logger.error("Duplicated Book tried to be added");
            throw new DuplicateBookException("Book With this title and author already exists");
        }

        return bookRepository.save(book);
    }

    public Book UpdateBook(Long id,@Valid Book book){

        Book book1 =  bookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Book","BookId", Math.toIntExact(book.getBookId())));

        book1.setAuthor(book.getAuthor());
        book1.setTitle(book.getTitle());

        return bookRepository.save(book1);

    }

    public void DeleteBook(Long BookId){
        Book book =  bookRepository.findById(BookId).orElseThrow(()->new ResourceNotFoundException("Book","BookId", Math.toIntExact(BookId)));

        bookRepository.delete(book);

    }

    public List<Book> searchBookByAuthor(String author){
        if(!bookRepository.existsByAuthor(author)){

            throw new ResourceNotFoundException("Author Name", author, 0);
        }
        return bookRepository.findByAuthor(author);
    }

}

