package fmi.project.controllers;

import fmi.project.models.BookModel;
import fmi.project.services.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("filter")
public class FilterController {

    @Autowired
    private FilterService filterService;

    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    @GetMapping("/author/{author}")
    public List<BookModel> filterByAuthor(@PathVariable String author){
        return filterService.filterByAuthor(author);
    }

    @GetMapping("/genre/{genre}")
    public List<BookModel> filterByGenre(@PathVariable String genre){
        return filterService.filterByGenre(genre);
    }
}
