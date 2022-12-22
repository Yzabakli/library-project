package com.abakli.client;

import com.abakli.dto.BookApiDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BookClientWeb {

    private final WebClient webClient = WebClient.builder().baseUrl("http://openlibrary.org").build();

    public BookApiDTO getBooks() {

        return webClient
                .get()
                .uri("/subjects/love.json")
                .retrieve()
                .bodyToMono(BookApiDTO.class).blockOptional().orElseThrow();

    }

    public BookApiDTO getBooksBySubject(String subject) {

        return webClient
                .get()
                .uri("/subjects/{subject}.json", subject)
                .retrieve()
                .bodyToMono(BookApiDTO.class).blockOptional().orElseThrow();

    }

    public BookApiDTO getBookByKey(String key) {

        return webClient
                .get()
                .uri("/books/{key}.json", key)
                .retrieve()
                .bodyToMono(BookApiDTO.class).blockOptional().orElseThrow();

    }
}
