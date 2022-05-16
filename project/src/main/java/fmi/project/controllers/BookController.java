package fmi.project.controllers;

import fmi.project.dto.BookDto;
import fmi.project.dto.DtoConverter;
import fmi.project.models.BookModel;
import fmi.project.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private DtoConverter dtoConverter;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getBooks(){
        return dtoConverter.toListDto(bookService.getBooks());
    }

    @PostMapping
    public BookDto addBook(@RequestBody BookModel bookModel){
        return dtoConverter.bookToDto(bookService.createBook(bookModel));
    }

    @PutMapping
    public BookDto updateBook(@RequestBody BookModel bookModel){
        return dtoConverter.bookToDto(bookService.updateBook(bookModel));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }
}
