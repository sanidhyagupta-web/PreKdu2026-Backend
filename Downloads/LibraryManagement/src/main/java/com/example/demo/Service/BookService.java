package com.example.demo.Service;

import com.example.demo.Exception.DuplicateBookException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Models.Book;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Utilities.BookResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private final List<Book> books = new CopyOnWriteArrayList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private RestClient restClient;

    @Retryable(
            retryFor = RuntimeException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    public Book GetBook(Long BookId){
        Book book = bookRepository.findById(BookId).orElseThrow(()->{
            logger.error("Error thrown for id {} while getting a book",BookId);
            return new ResourceNotFoundException("Book","BookId", Math.toIntExact(BookId));
        });

           String isbn = restClient.get()
                .uri("/covers/{isbn}",BookId)
                .retrieve()
                   .body(String.class);

           book.setIsbn(isbn);
           return book;

    }

    @Recover
    private void recover(RuntimeException ex){
        System.out.println("System Failed : " + ex.getMessage());
    }

    public BookResponse findAllBooks(Integer PageSize, Integer PageNumber,String sortBy,String sortDir){
        logger.info("Getting all the books");
        Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable p = PageRequest.of(PageNumber,PageSize,sort);
        Page<Book> page = bookRepository.findAll(p);

        BookResponse bookResponse = BookResponse.builder()
                .books(page.getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .lastPage(page.isLast())
                .build();

        return bookResponse;
    }


    public Book AddBook(@Valid Book book)  {
        logger.info("Adding new book: {} by {}", book.getTitle(), book.getAuthor());

        if(bookRepository.existsByTitle(book.getTitle()) && bookRepository.existsByAuthor(book.getAuthor())){
            logger.error("Duplicated Book tried to be added");
            throw new DuplicateBookException("Book With this title and author already exists");
        }

        // MAIN-THREAD Task
        book.setStatus("PROCESSING");
        Book book1 =  bookRepository.save(book);

        // BACKGROUND Task
        executorService.submit(()->printBarcode(book.getBookId()));

        return book1;
    }

    private void printBarcode(Long id) {
        try {
            Thread.sleep(3000);
            Book book = bookRepository.findById(id).orElseThrow(()->{
                logger.error("Error thrown for id {} while getting barcode",id);
                return new ResourceNotFoundException("Book","BookId", Math.toIntExact(id));
            });

            book.setStatus("COMPLETED");
            bookRepository.save(book);

            //Intensive Operation
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    public Book UpdateBook(Long id,@Valid Book book){

        Book book1 =  bookRepository.findById(id).orElseThrow(()->{
            logger.error("Error thrown for id {} while updating the book",id);
            return new ResourceNotFoundException("Book","BookId", Math.toIntExact(book.getBookId()));
        });

        book1.setAuthor(book.getAuthor());
        book1.setTitle(book.getTitle());

        return bookRepository.save(book1);

    }

    @Async
    public void DeleteBook(Long BookId){
        System.out.println("Running by thread : " + Thread.currentThread().getName());
        Book book =  bookRepository.findById(BookId).orElseThrow(()->new ResourceNotFoundException("Book","BookId", Math.toIntExact(BookId)));

        bookRepository.delete(book);

    }

    public List<Book> searchBookByAuthor(String author){
        if(!bookRepository.existsByAuthor(author)){

            throw new ResourceNotFoundException("Author Name", author, 0);
        }
        return bookRepository.findByAuthor(author);
    }

    public Map<String, Long> getAnalytics() {
        Map<String,Long> map = bookRepository.findAll().
                                stream().
                                collect(Collectors.groupingBy(x->x.getStatus(),Collectors.counting()));
        return map;
    }
}