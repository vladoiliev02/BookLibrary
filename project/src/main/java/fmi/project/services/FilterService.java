package fmi.project.services;

import fmi.project.models.BookModel;
import fmi.project.repository.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterService {

    @Autowired
    private FilterRepository filterRepository;

    public FilterService(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

    public List<BookModel> filterByAuthor(String author) {
        return filterRepository.findByAuthor(author);
    }

    public List<BookModel> filterByGenre(String genre) {
        return filterRepository.findByGenre(genre);
    }

}
