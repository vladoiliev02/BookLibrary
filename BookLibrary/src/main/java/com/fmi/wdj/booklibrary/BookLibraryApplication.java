package com.fmi.wdj.booklibrary;

import com.fmi.wdj.booklibrary.model.book.BookGenre;
import com.fmi.wdj.booklibrary.model.book.Genre;
import com.fmi.wdj.booklibrary.repository.book.BookGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class BookLibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookLibraryApplication.class, args);
    }

    // CHANGE LATER !!!!!!
    // initialize the genres with all values of the Genre enum
    @Autowired
    private BookGenreRepository bookRepository;
    @PostConstruct
    void init() {
        if (bookRepository.findAll().isEmpty()) {
            Arrays.stream(Genre.values()).forEach(g -> bookRepository.save(new BookGenre(g)));
        }
    }
}
