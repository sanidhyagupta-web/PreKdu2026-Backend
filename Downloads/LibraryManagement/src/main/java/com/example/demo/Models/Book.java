package com.example.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3 , max = 100 , message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank
    @Size(min = 3 , max = 50 , message = "Author name must be between 3 to 50 characters")
    private String author;

    private String isbn;

    private String ImageCoverURL;

}