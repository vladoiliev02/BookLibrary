package fmi.project.controllers;

import fmi.project.dto.BookDto;
import fmi.project.models.BookModel;
import fmi.project.models.ListModel;
import fmi.project.services.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping(path = "booklist")
public class BookListController {

    @Autowired
    private BookListService bookListService;

    public BookListController(BookListService bookListService) {
        this.bookListService = bookListService;
    }

    @GetMapping
    public List<ListModel> getLists(){
        return bookListService.getLists();
    }

    @GetMapping("search/{list_id}/{book_name}")
    public BookModel searchBook(@PathVariable Long list_id, @PathVariable String book_name){
        return bookListService.searchBook(list_id, book_name);
    }

    @PostMapping
    public ListModel createList(String listName){
        return bookListService.createList(listName);
    }

    @PostMapping("addToList/{list_id}/book/{book_id}")
    public ListModel addBookToList(@PathVariable Long list_id, @PathVariable Long book_id){
        return bookListService.addToList(list_id, book_id);
    }

    @PostMapping("/{listName}")
    public ListModel getByListName(@PathVariable String listName){
        return bookListService.getByListName(listName);
    }

    @PostMapping("deleteFromList/{list_id}/book/{book_id}")
    public ListModel deleteFromList(@PathVariable Long list_id, @PathVariable Long book_id){
        return bookListService.deleteFromList(list_id, book_id);
    }
}
