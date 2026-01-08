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
    public Map<String, String> getCover(@PathVariable String isbn) {
        Map<String, String> map = new HashMap<>();
        map.put("imageUrl", "https://cdn.registry.com/covers/" + isbn + ".jpg");
        return map;
    }
}

