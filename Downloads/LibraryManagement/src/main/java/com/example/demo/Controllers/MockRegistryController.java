package com.example.demo.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mock")
public class MockRegistryController {

    @GetMapping("/covers/{isbn}")
    public String getCover(@PathVariable String isbn) {
        return  "https://cdn.registry.com/covers/" + isbn + ".jpg";

    }
}

