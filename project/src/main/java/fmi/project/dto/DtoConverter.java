package fmi.project.dto;

import fmi.project.models.BookModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoConverter {

    public BookDto bookToDto(BookModel book){
        BookDto bookDto = new BookDto(book.getBook_id(), book.getAuthor(), book.getGenre(), book.getTitle(), book.getLists());
        return bookDto;
    }

    public BookModel dtoToBook(BookDto bookDto){
        BookModel book = new BookModel(bookDto.getBook_id(), bookDto.getAuthor(), bookDto.getGenre(), bookDto.getTitle(), bookDto.getList());
        return book;
    }

    public List<BookDto> toListDto(List<BookModel> list_books){
        return list_books.stream().map(x -> bookToDto(x)).collect(Collectors.toList());
    }

    public List<BookModel> toListBooks(List<BookDto> list_dtos){
        return list_dtos.stream().map(x -> dtoToBook(x)).collect(Collectors.toList());
    }

}
