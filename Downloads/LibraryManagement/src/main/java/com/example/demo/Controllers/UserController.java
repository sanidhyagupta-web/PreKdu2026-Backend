package com.example.demo.Controllers;

import com.example.demo.Models.Book;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(userService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getBookById(id),HttpStatus.OK);
    }

//    @GetMapping("/test")
//    public ResponseEntity<String> doSomething() throws InterruptedException, ExecutionException {
//        return new ResponseEntity<>(userService.doSomething(),HttpStatus.OK);
//    }

}
