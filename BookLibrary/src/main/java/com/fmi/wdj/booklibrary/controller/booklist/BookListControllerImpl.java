package com.fmi.wdj.booklibrary.controller.booklist;

import com.fmi.wdj.booklibrary.dto.booklist.BookListDto;
import com.fmi.wdj.booklibrary.mapper.booklist.BookListMapper;
import com.fmi.wdj.booklibrary.model.book.Book;
import com.fmi.wdj.booklibrary.model.booklist.BookList;
import com.fmi.wdj.booklibrary.service.book.BookService;
import com.fmi.wdj.booklibrary.service.booklist.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book-lists")
public class BookListControllerImpl implements BookListController {

    private final BookListService bookListService;
    private final BookListMapper bookListMapper;
    private final BookService bookService;

    @Autowired
    public BookListControllerImpl(BookListService bookListService, BookListMapper bookListMapper,
                                  BookService bookService) {
        this.bookListService = bookListService;
        this.bookListMapper = bookListMapper;
        this.bookService = bookService;
    }

    @Override
    @GetMapping("/all")
    public List<BookListDto> getAllList() {
        return bookListService.getAllLists()
            .stream()
            .map(bookListMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    @GetMapping("/{username}")
    public List<BookListDto> getAllByUser(@PathVariable String username) {
        return bookListService.getByUser(username)
            .stream()
            .map(bookListMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    @GetMapping("/{bookListName}")
    public List<BookListDto> getAllWithName(@PathVariable String bookListName) {
        return bookListService.getByName(bookListName)
            .stream()
            .map(bookListMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    @GetMapping("/{username}/{bookListName}")
    public BookListDto getUserListByName(@PathVariable String username, @PathVariable String bookListName) {
        return bookListService.getByNameAndUser(bookListName, username)
            .map(bookListMapper::toDto)
            .orElseThrow(() -> new IllegalArgumentException(String.format(
                "User %s, has no list with name %s", username, bookListName
            )));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookListDto createBookList(@RequestBody @Valid BookListDto bookListDto) {
        BookList newList = bookListMapper.fromDto(bookListDto);
        newList = bookListService.createList(newList);
        return bookListMapper.toDto(newList);
    }

    @Override
    @PutMapping
    public ResponseEntity<BookListDto> updateBookList(@RequestBody @Valid BookListDto bookListDto,
                                                      Principal principal) {
        boolean exists = bookListService.getByNameAndUser(bookListDto.getName(), bookListDto.getOwnerUsername())
            .isPresent();

        BookList list = bookListMapper.fromDto(bookListDto);

        BookListDto result;
        HttpStatus resultStatus;
        if (exists) {
            BookList updated = bookListService.updateList(list);
            result = bookListMapper.toDto(updated);
            resultStatus = HttpStatus.OK;
        } else {
            BookList newList = bookListService.createList(list);
            result = bookListMapper.toDto(newList);
            resultStatus = HttpStatus.CREATED;
        }

        return new ResponseEntity<>(result, resultStatus);
    }

    @Override
    @PatchMapping("/add")
    public BookListDto addBook(@RequestParam String bookListName, @RequestParam String isbn, Principal principal) {
        BookList list = bookListService.getByNameAndUser(bookListName, principal.getName())
            .orElseThrow(() -> new IllegalArgumentException(String.format(
                "You do not own a list with the name: %s.", bookListName
            )));

        bookListService.addBook(list, isbn);

        return bookListMapper.toDto(list);
    }

    @Override
    @PatchMapping("/remove")
    public BookListDto removeBook(@RequestParam String bookListName, @RequestParam String isbn, Principal principal) {
        BookList list = bookListService.getByNameAndUser(bookListName, principal.getName())
            .orElseThrow(() -> new IllegalArgumentException(String.format(
                "You do not own a list with the name: %s.", bookListName
            )));

        bookListService.removeBook(list, isbn);

        return bookListMapper.toDto(list);
    }

    @Override
    public void removeBookList(String name, Principal principal) {
        BookList list = bookListService.getByNameAndUser(name, principal.getName())
            .orElseThrow(() -> new IllegalArgumentException(String.format(
                "You do not own a list with the name: %s.", name
            )));

        bookListService.deleteList(list.getBookListId());
    }
}
