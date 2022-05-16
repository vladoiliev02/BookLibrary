package fmi.project.services;

import fmi.project.dto.BookDto;
import fmi.project.dto.DtoConverter;
import fmi.project.models.BookModel;
import fmi.project.models.ListModel;
import fmi.project.repository.BookListRepository;
import fmi.project.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookListService {

    @Autowired
    private BookListRepository bookListRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private DtoConverter dtoConverter;

    public BookListService(BookListRepository bookListRepository, BookRepository bookRepository, DtoConverter dtoConverter) {
        this.bookListRepository = bookListRepository;
        this.bookRepository = bookRepository;
        this.dtoConverter = dtoConverter;
    }

    public List<ListModel> getLists() {
        return bookListRepository.findAll();
    }

    public ListModel createList(String listName) {
        ListModel list = new ListModel(listName);
        return bookListRepository.save(list);
    }

    public ListModel addToList(Long list_id, Long book_id) {

        Optional<BookModel> book = bookRepository.findById(book_id);
        Optional<ListModel> list = bookListRepository.findById(list_id);

        if(list.isPresent()){
            ListModel selectedList = list.get();

            if(book.isPresent()){
                BookModel selectedBook = book.get();
                selectedList.addBook(selectedBook);
                return bookListRepository.save(selectedList);
            }
        }
        return null;
    }

    public ListModel getByListName(String listName) {
        return bookListRepository.getByListName(listName);
    }

    public ListModel deleteFromList(Long list_id, Long book_id) {
        Optional<ListModel> list = bookListRepository.findById(list_id);
        Optional<BookModel> book = bookRepository.findById(book_id);

        if(list.isPresent()){
            if(book.isPresent()){
                ListModel selectedList = list.get();
                BookModel selectedBook = book.get();

                selectedList.removeBook(selectedBook);
                return bookListRepository.save(selectedList);
            }
        }
        return null;
    }

    public BookModel searchBook(Long list_id, String book_name) {
        Optional<ListModel> list = bookListRepository.findById(list_id);

        if(list.isPresent()){
            ListModel selectedList = list.get();
            return selectedList.searchBook(book_name);
        }
        return null;
    }
}
