package com.fmi.wdj.booklibrary.service.book;

import com.fmi.wdj.booklibrary.model.book.Book;
import com.fmi.wdj.booklibrary.model.book.Genre;

import java.util.List;

public interface BookService {

    Book saveBook(Book newBook);

    void removeBook(String ISBN);

    List<Book> getAllBooks();

    Book getBookByISBN(String isbn);

    boolean exists(String isbn);

    List<Book> getBooksByCriteria(String title, String author, String[] genres);
}
