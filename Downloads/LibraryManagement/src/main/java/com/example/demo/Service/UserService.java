package com.example.demo.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Models.Book;
import com.example.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserService {

    private final int cores = Runtime.getRuntime().availableProcessors();
    private ExecutorService executorService = Executors.newFixedThreadPool(cores);
    List<String> values = List.of("1","2","3","4","5","6","7","8","9","10");

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Book","id", Math.toIntExact(id)));
    }

//    public String doSomething() throws InterruptedException, ExecutionException {
//        StringBuffer stringBuffer = new StringBuffer();
//        List<Future<?>> futures = new ArrayList<>();
//
//        for(String x : values){
//            futures.add(executorService.submit(()->stringBuffer.append(x)));
//        }
//
//        for(Future<?> f : futures){
//            f.get();
//        }
//
//        return stringBuffer.toString();
//    }

}
