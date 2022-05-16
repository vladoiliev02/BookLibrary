package fmi.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "books")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "book_sequence_name")
    @SequenceGenerator(name = "book_sequence_gen", sequenceName = "BOOK_GEN")
    @Column(name = "book_id")
    private Long book_id;

    @Column(name = "author")
    private String author;

    @Column(name = "genre")
    private String genre;

    @Column(name = "title")
    private String title;

    @Column(name = "isbn")
    private String ISBN;

    @Column(name = "lists")
    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private List<ListModel> lists;

    public BookModel() {
    }

    public BookModel(String author, String genre, String title, List<ListModel> lists) {
        this.author = author;
        this.genre = genre;
        this.title = title;
        this.lists = lists;
    }

    public BookModel(Long book_id, String author, String genre, String title, List<ListModel> lists) {
        this.book_id = book_id;
        this.author = author;
        this.genre = genre;
        this.title = title;
        this.lists = lists;
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

    public String getISBN() {
        return ISBN;
    }

    public List<ListModel> getLists() {
        return lists;
    }

    public void setLists(List<ListModel> lists) {
        this.lists = lists;
    }

    public void setISBN() {
        this.ISBN = UUID.randomUUID().toString();
    }
}
