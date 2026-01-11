package com.example.demo.Service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class BookCoverClient {

    private static final RestTemplate restTemplate = new RestTemplate() ;

    @Retryable(
            retryFor = { ResourceAccessException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000)
    )
    public static String fetchCoverImage(String isbn) {

        String url = "http://localhost:8081/mock/covers/" + isbn;

        return url;
    }

    @Recover
    public String recover(ResourceAccessException ex, String isbn) {
        // fallback image
        return "https://cdn.myapp.com/default-cover.png";
    }

}

