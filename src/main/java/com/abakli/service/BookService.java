package com.abakli.service;

import com.abakli.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> readAll();

    List<BookDTO> readAllFromAPI();

    List<BookDTO> readAllFromWeb();

    List<BookDTO> readAllFromRest();

    void borrow(Long id);

    void returnBook(Long id, Integer copies);

    BookDTO findById(Long bookId);

    void save(BookDTO bookDTO);

    void update(BookDTO bookDTO);

    void delete(Long id);

    List<BookDTO> getBySubjectFromAPI(String subject);

    List<BookDTO> getBySubjectFromWeb(String subject);

    List<BookDTO> getBySubjectFromRest(String subject);

    List<BookDTO> getByTitleContains(String keyword);

    BookDTO saveFromAPI(String key);
}
