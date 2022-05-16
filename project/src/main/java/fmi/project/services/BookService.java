package fmi.project.services;

import fmi.project.models.BookModel;
import fmi.project.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookModel> getBooks() {
        return bookRepository.findAll();
    }

    public BookModel createBook(BookModel bookModel) {
        bookModel.setISBN();
        return bookRepository.save(bookModel);
    }

    public BookModel updateBook(BookModel bookModel) {
        return bookRepository.save(bookModel);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
