package com.fmi.wdj.booklibrary.service.notes;

import com.fmi.wdj.booklibrary.model.book.Book;
import com.fmi.wdj.booklibrary.model.notes.Note;
import com.fmi.wdj.booklibrary.model.notes.NoteData;
import com.fmi.wdj.booklibrary.model.user.User;
import com.fmi.wdj.booklibrary.repository.book.BookRepository;
import com.fmi.wdj.booklibrary.repository.notes.NoteDataRepository;
import com.fmi.wdj.booklibrary.repository.notes.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final BookRepository bookRepository;
    private final NoteDataRepository noteDataRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(BookRepository bookRepository, NoteDataRepository noteDataRepository, NoteRepository noteRepository) {
        this.bookRepository = bookRepository;
        this.noteDataRepository = noteDataRepository;
        this.noteRepository = noteRepository;
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

        Note persistedNote = noteRepository.save(new Note(book, note, noteData));
        noteData.getNotes().add(persistedNote);
        noteDataRepository.save(noteData);
    }

    @Override
    public void removeNote(long id, User owner) {
        NoteData noteData = noteDataRepository.findByOwner(owner)
            .orElseGet(() -> noteDataRepository.save(new NoteData(owner, new ArrayList<>())));

        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Note: %d, not found.", id)));

        if (!note.getNoteData().equals(noteData)) {
            throw new IllegalArgumentException("This note belongs to a different user.");
        }

        noteRepository.deleteById(id);
    }


}
