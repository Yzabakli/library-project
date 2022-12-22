package com.abakli.repository;

import com.abakli.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainsOrderByTitleAsc(String title);

}