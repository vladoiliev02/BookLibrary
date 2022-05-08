package com.fmi.wdj.booklibrary.mapper.booklist;

import com.fmi.wdj.booklibrary.dto.booklist.BookListDto;
import com.fmi.wdj.booklibrary.model.booklist.BookList;
import com.fmi.wdj.booklibrary.model.user.User;
import com.fmi.wdj.booklibrary.service.booklist.BookListService;
import com.fmi.wdj.booklibrary.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookListMapper {

    private final BookListService bookListService;
    private final UserService userService;

    @Autowired
    public BookListMapper(BookListService bookListService, UserService userService) {
        this.bookListService = bookListService;
        this.userService = userService;
    }

    public BookListDto toDto(BookList bookList) {
        BookListDto dto = new BookListDto();

        dto.setName(bookList.getName());
        dto.setOwnerUsername(bookList.getOwner().getUsername());
        dto.setBooks(bookList.getBooks());

        return dto;
    }

    public BookList fromDto(BookListDto dto) {
        BookList bookList = new BookList();

        bookList.setName(dto.getName());
        bookList.setBooks(dto.getBooks());

        User owner = userService.getUserByUsername(dto.getOwnerUsername())
            .orElseThrow(() -> new IllegalArgumentException(
                String.format("User %s, not found.", dto.getOwnerUsername())));
        bookList.setOwner(owner);

        Long oldListId = bookListService.getByNameAndUser(dto.getName(), dto.getOwnerUsername())
            .map(BookList::getBookListId)
            .orElse(null);

        bookList.setBookListId(oldListId);

        return bookList;
    }
}
