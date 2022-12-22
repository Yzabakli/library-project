package com.abakli.service.impl;

import com.abakli.client.BookClient;
import com.abakli.client.BookClientRest;
import com.abakli.client.BookClientWeb;
import com.abakli.dto.BookApiDTO;
import com.abakli.dto.BookDTO;
import com.abakli.dto.WorkDTO;
import com.abakli.entity.Book;
import com.abakli.mapper.MapperUtil;
import com.abakli.repository.BookRepository;
import com.abakli.service.BookService;
import com.abakli.service.BorrowerRecordService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BorrowerRecordService borrowerRecordService;
    private final BookClient bookClient;
    private final BookClientWeb bookClientWeb;
    private final BookClientRest bookClientRest;
    private final MapperUtil mapper;

    public BookServiceImpl(BookRepository bookRepository, @Lazy BorrowerRecordService borrowerRecordService, BookClient bookClient, BookClientWeb bookClientWeb, BookClientRest bookClientRest, MapperUtil mapper) {
        this.bookRepository = bookRepository;
        this.borrowerRecordService = borrowerRecordService;
        this.bookClient = bookClient;
        this.bookClientWeb = bookClientWeb;
        this.bookClientRest = bookClientRest;
        this.mapper = mapper;
    }

    @Override
    public List<BookDTO> readAll() {
        return bookRepository.findAll()
                .stream()
                .map(book -> mapper.convert(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> readAllFromAPI() {
        return bookClient.getBooks()
                .getWorks()
                .stream()
                .map(work -> new BookDTO(work.getCoverId().longValue(), work.getTitle(), work.getEditionCount()
                        .toString(), work.getAuthors()
                        .get(0)
                        .getName(), "Unknown Publisher", 1, "", BigDecimal.valueOf(10), "Amazing!", work.getKey()
                        .substring(7)))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> readAllFromWeb() {
        return bookClientWeb.getBooks().getWorks().stream()
                .map(work -> new BookDTO(work.getCoverId().longValue(), work.getTitle(), work.getEditionCount()
                        .toString(), work.getAuthors()
                        .get(0)
                        .getName(), "Unknown Publisher", 1, "", BigDecimal.valueOf(10), "Amazing!", work.getKey()
                        .substring(7)))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> readAllFromRest() {
        return bookClientRest.getBooks().getWorks().stream()
                .map(work -> new BookDTO(work.getCoverId().longValue(), work.getTitle(), work.getEditionCount()
                        .toString(), work.getAuthors()
                        .get(0)
                        .getName(), "Unknown Publisher", 1, "", BigDecimal.valueOf(10), "Amazing!", work.getKey()
                        .substring(7)))
                .collect(Collectors.toList());
    }

    @Override
    public void borrow(Long id) {

        borrowerRecordService.findByUserContactUnreturned(id);

        Book book = bookRepository.findById(id).orElseThrow();

        book.setCopies(book.getCopies() - 1);

        bookRepository.save(book);
    }

    @Override
    public void returnBook(Long id, Integer copies) {

        Book book = bookRepository.findById(id).orElseThrow();

        book.setCopies(book.getCopies() + copies);

        bookRepository.save(book);
    }

    @Override
    public BookDTO findById(Long bookId) {
        return mapper.convert(bookRepository.findById(bookId), BookDTO.class);
    }

    @Override
    public void save(BookDTO bookDTO) {

        bookRepository.save(mapper.convert(bookDTO, Book.class));
    }

    @Override
    public void update(BookDTO bookDTO) {

        Book book = bookRepository.findById(bookDTO.getId()).orElseThrow();

        Book convert = mapper.convert(bookDTO, Book.class);

        convert.setId(book.getId());

        bookRepository.save(convert);
    }

    @Override
    public void delete(Long id) {

        Book book = bookRepository.findById(id).orElseThrow();

        book.setIsDeleted(true);

        bookRepository.save(book);
    }

    @Override
    public List<BookDTO> getBySubjectFromAPI(String subject) {

        return getBookDTOS(bookClient.getBooksBySubject(subject));
    }

    @Override
    public List<BookDTO> getBySubjectFromWeb(String subject) {

        return getBookDTOS(bookClientWeb.getBooksBySubject(subject));
    }

    @Override
    public List<BookDTO> getBySubjectFromRest(String subject) {

        return getBookDTOS(bookClientRest.getBooksBySubject(subject));
    }

    @Override
    public List<BookDTO> getByTitleContains(String keyword) {
        try {

            return bookRepository.findByTitleContainsOrderByTitleAsc(keyword)
                    .stream()
                    .map(book -> mapper.convert(book, BookDTO.class))
                    .collect(Collectors.toList());

        } catch (NullPointerException e) {

            return new ArrayList<>();
        }
    }

    @Override
    public BookDTO saveFromAPI(String key) {

        WorkDTO work = bookClient.getBookByKey(key);

        return mapper.convert(bookRepository.save(mapper.convert(new BookDTO(work.getCoverId()
                .longValue(),
                work.getTitle(),
                work.getEditionCount()
                .toString(),
                work.getAuthors().get(0).getName(),
                "Unknown Publisher",
                1,
                ("https://openlibrary.org/books/" + key),
                BigDecimal.valueOf(10),
                "Amazing!",
                work.getKey().substring(7)
        ), Book.class)), BookDTO.class);
    }

    private List<BookDTO> getBookDTOS(BookApiDTO booksBySubject) {

        List<WorkDTO> books = booksBySubject.getWorks();

        if (!books.isEmpty()) {

            return books
                    .stream()
                    .map(work -> new BookDTO(work.getCoverId().longValue(), work.getTitle(), work.getEditionCount()
                            .toString(), work.getAuthors()
                            .get(0)
                            .getName(), "Unknown Publisher", 1, "", BigDecimal.valueOf(10), "Amazing!", work.getKey()
                            .substring(7)))
                    .collect(Collectors.toList());

        }

        return new ArrayList<>();
    }
}
