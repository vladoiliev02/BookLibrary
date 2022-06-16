package com.fmi.wdj.booklibrary.controller.notes;

import com.fmi.wdj.booklibrary.dto.notes.NewNoteDto;
import com.fmi.wdj.booklibrary.dto.notes.NoteDto;

import java.security.Principal;
import java.util.List;

public interface NoteController {

    NoteDto getAllNotes(Principal principal);

    List<String> getNotesForBook(String isbn, Principal principal);

    String addNote(NewNoteDto newNoteDto, Principal principal);

    void removeNote(long id, Principal principal);
}
