package com.fmi.wdj.booklibrary.service.notes;

import com.fmi.wdj.booklibrary.model.book.Book;
import com.fmi.wdj.booklibrary.model.notes.Note;
import com.fmi.wdj.booklibrary.model.notes.NoteData;
import com.fmi.wdj.booklibrary.model.user.User;
import com.fmi.wdj.booklibrary.repository.book.BookRepository;
import com.fmi.wdj.booklibrary.repository.notes.NoteDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final BookRepository bookRepository;
    private final NoteDataRepository noteDataRepository;

    @Autowired
    public NoteServiceImpl(BookRepository bookRepository, NoteDataRepository noteDataRepository) {
        this.bookRepository = bookRepository;
        this.noteDataRepository = noteDataRepository;
    }

    @Override
    public NoteData getNoteDataForUser(User owner) {
        return noteDataRepository.findByOwner(owner)
            .orElseGet(() -> noteDataRepository.save(new NoteData(owner, new ArrayList<>())));
    }

    @Override
    public List<Note>  getNotesForBook(String isbn, User owner) {
        Book book = bookRepository.findById(isbn)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Book with isbn: %s, not found", isbn)));
        NoteData noteData = noteDataRepository.findByOwner(owner)
            .orElseGet(() -> noteDataRepository.save(new NoteData(owner, new ArrayList<>())));

        return noteData.getNotes();
    }

    @Override
    public void addNote(String isbn, String note, User owner) {
        Book book = bookRepository.findById(isbn)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Book with isbn: %s, not found", isbn)));
        NoteData noteData = noteDataRepository.findByOwner(owner)
            .orElseGet(() -> noteDataRepository.save(new NoteData(owner, new ArrayList<>())));

        noteData.getNotes().add(new Note(book, note, noteData));
        noteDataRepository.save(noteData);
    }

    @Override
    public void removeNote(String isbn, int position, User owner) {
        Book book = bookRepository.findById(isbn)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Book with isbn: %s, not found", isbn)));
        NoteData noteData = noteDataRepository.findByOwner(owner)
            .orElseGet(() -> noteDataRepository.save(new NoteData(owner, new ArrayList<>())));

        noteData.getNotes().remove(position);
        noteDataRepository.save(noteData);
    }


}
