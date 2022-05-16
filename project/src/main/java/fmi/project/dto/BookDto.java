package fmi.project.dto;

import fmi.project.models.ListModel;

import javax.persistence.Entity;
import java.util.List;
import lombok.Data;

@Data
public class BookDto {
    private Long book_id;
    private String author;
    private String genre;
    private String title;
    private List<ListModel> list;

    public BookDto() {
    }

    public BookDto(Long book_id, String author, String genre, String title, List<ListModel> list) {
        this.book_id = book_id;
        this.author = author;
        this.genre = genre;
        this.title = title;
        this.list = list;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ListModel> getList() {
        return list;
    }

    public void setList(List<ListModel> list) {
        this.list = list;
    }
}
