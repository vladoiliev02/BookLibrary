package fmi.project.models;

import fmi.project.dto.BookDto;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "booklist")
public class ListModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "list_sequence_gen")
    @SequenceGenerator(name = "list_sequence_gen", sequenceName = "LIST_GEN")
    private Long list_id;

    @Column
    private String listName;

    @Column
    @ManyToMany()
    @JoinTable(name = "books_lists",
    joinColumns = @JoinColumn(name = "list_id"),
    inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<BookModel> books;

    public ListModel(){

    }

    public ListModel(String listName) {
        this.listName = listName;
    }

    public ListModel(Long list_id, String listName, Set<BookModel> books) {
        this.list_id = list_id;
        this.listName = listName;
        this.books = books;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Long getList_id() {
        return list_id;
    }

    public void setList_id(Long list_id) {
        this.list_id = list_id;
    }

    public Set<BookModel> getBooks() {
        return books;
    }

    public void setBooks(Set<BookModel> books) {
        this.books = books;
    }

    public void addBook(BookModel book){
        books.add(book);
    }

    public void removeBook(BookModel book){
        books.remove(book);
    }

    public BookModel searchBook(String book_name){
        return this.books.stream().filter(b -> b.getTitle().equals(book_name)).findFirst().orElse(null);
    }
}
