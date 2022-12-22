package com.abakli.client;

import com.abakli.dto.BookApiDTO;
import com.abakli.dto.WorkDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://openlibrary.org", name = "BOOK-CLIENT")
public interface BookClient {

    @GetMapping("/subjects/love.json")
    BookApiDTO getBooks();

    @GetMapping("/subjects/{subject}.json")
    BookApiDTO getBooksBySubject(@PathVariable String subject);

    @GetMapping("/books/{key}.json")
    WorkDTO getBookByKey(@PathVariable String key);
}
