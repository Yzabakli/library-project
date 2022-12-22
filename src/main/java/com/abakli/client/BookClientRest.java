package com.abakli.client;

import com.abakli.dto.BookApiDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class BookClientRest {

    private final RestTemplate restTemplate;
    private final String URI = "http://openlibrary.org";

    public BookClientRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BookApiDTO getBooks() {

        return restTemplate.getForObject(URI + "/subjects/love.json", BookApiDTO.class);
    }

    public BookApiDTO getBooksBySubject(String subject) {

        HttpHeaders headers = new HttpHeaders();

        return restTemplate.getForObject(URI + "/subjects/{subject}.json", BookApiDTO.class, subject, headers);
    }

    public BookApiDTO getBookByKey(String key) {

        return restTemplate.getForObject(URI + key, BookApiDTO.class);
    }
}
