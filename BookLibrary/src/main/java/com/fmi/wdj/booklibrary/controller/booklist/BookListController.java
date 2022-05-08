package com.fmi.wdj.booklibrary.controller.booklist;

import com.fmi.wdj.booklibrary.dto.booklist.BookListDto;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface BookListController {

    List<BookListDto> getAllList();

    List<BookListDto> getAllByUser(String username);

    List<BookListDto> getAllWithName(String bookListName);

    BookListDto getUserListByName(String username, String bookListName);

    BookListDto createBookList(BookListDto bookListDto);

    ResponseEntity<BookListDto> updateBookList(BookListDto bookListDto, Principal principal);

    BookListDto addBook(String bookListName, String isbn, Principal principal);

    BookListDto removeBook(String bookListName, String isbn, Principal principal);

    void removeBookList(String name, Principal principal);
}