package com.fmi.wdj.booklibrary.controller.notes;

import com.fmi.wdj.booklibrary.dto.notes.NewNoteDto;
import com.fmi.wdj.booklibrary.dto.notes.NoteDto;
import com.fmi.wdj.booklibrary.mapper.notes.NoteMapper;
import com.fmi.wdj.booklibrary.model.notes.Note;
import com.fmi.wdj.booklibrary.model.notes.NoteData;
import com.fmi.wdj.booklibrary.model.user.User;
import com.fmi.wdj.booklibrary.service.notes.NoteService;
import com.fmi.wdj.booklibrary.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
public class NoteControllerImpl implements NoteController {

    private final NoteService noteService;
    private final NoteMapper noteMapper;
    private final UserService userService;

    @Autowired
    public NoteControllerImpl(NoteService noteService, NoteMapper noteMapper, UserService userService) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    @Override
    @GetMapping("/all")
    public NoteDto getAllNotes(Principal principal) {
        User owner = userService.getUserByUsername(principal.getName())
            .orElseThrow(() -> new IllegalStateException(String.format("User: %s, not found", principal.getName())));
        NoteData noteData = noteService.getNoteDataForUser(owner);
        return noteMapper.toNoteDto(noteData);
    }

    @Override
    @GetMapping("/{isbn}")
    public List<String> getNotesForBook(@PathVariable String isbn, Principal principal) {
        User owner = userService.getUserByUsername(principal.getName())
            .orElseThrow(() -> new IllegalStateException(String.format("User: %s, not found", principal.getName())));
        List<Note> notes = noteService.getNotesForBook(isbn, owner);

        return notes.stream()
            .map(Note::getContent)
            .collect(Collectors.toList());
    }

    @Override
    @PatchMapping
    public String addNote(@RequestBody @Valid NewNoteDto newNoteDto, Principal principal) {
        User owner = userService.getUserByUsername(principal.getName())
            .orElseThrow(() -> new IllegalStateException(String.format("User: %s, not found", principal.getName())));
        noteService.addNote(newNoteDto.getISBN(), newNoteDto.getContent(), owner);
        return newNoteDto.getContent();
    }

    @Override
    @DeleteMapping("/{isbn}/{position}")
    public void removeNote(@PathVariable String isbn, @PathVariable int position, Principal principal) {
        User owner = userService.getUserByUsername(principal.getName())
            .orElseThrow(() -> new IllegalStateException(String.format("User: %s, not found", principal.getName())));
        noteService.removeNote(isbn, position, owner);
    }
}
