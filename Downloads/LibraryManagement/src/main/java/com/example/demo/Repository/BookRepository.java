package com.example.demo.Repository;


import com.example.demo.Models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book,Long> {
    boolean existsByTitle(String title);
    boolean existsByAuthor(String author);
    List<Book> findByAuthor(String author);

    Page<Book> findByAuthorIgnoreCase(String author, Pageable pageable);
}