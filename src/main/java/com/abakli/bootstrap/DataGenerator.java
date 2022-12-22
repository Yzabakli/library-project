package com.abakli.bootstrap;

import com.abakli.entity.Book;
import com.abakli.repository.BookRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataGenerator implements CommandLineRunner {

    private final Faker faker;
    private final BookRepository repository;

    public DataGenerator(Faker faker, BookRepository repository) {
        this.faker = faker;
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {

            repository.save(new Book(faker.book().title(), "1.", faker.book().author(), faker.company()
                    .name(), 2, faker.internet().url(), BigDecimal.valueOf(faker.number().randomNumber()), faker.lorem()
                    .sentence()));
        }
    }
}
