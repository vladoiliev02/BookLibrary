package com.fmi.wdj.booklibrary.model.notes;

import com.fmi.wdj.booklibrary.model.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "note_data")
public class Note {

    @Id
    @Column(name = "note_id")
    private Long id;

    @OneToOne
    private Book book;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    private NoteData noteData;

    public Note(Book book, String content, NoteData noteData) {
        this.book = book;
        this.content = content;
        this.noteData = noteData;
    }
}
