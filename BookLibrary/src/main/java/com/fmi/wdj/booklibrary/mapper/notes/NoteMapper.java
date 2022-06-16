package com.fmi.wdj.booklibrary.mapper.notes;

import com.fmi.wdj.booklibrary.dto.notes.NoteDataDto;
import com.fmi.wdj.booklibrary.dto.notes.ResultNoteDto;
import com.fmi.wdj.booklibrary.model.notes.Note;
import com.fmi.wdj.booklibrary.model.notes.NoteData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class NoteMapper {

    public NoteDataDto toNoteDto(NoteData noteData) {
        NoteDataDto result = new NoteDataDto();
        Map<String, List<ResultNoteDto>> resultMap = new HashMap<>();

        for (Note note : noteData.getNotes()) {
            String isbn = note.getBook().getISBN();

            if (!resultMap.containsKey(isbn)) {
                resultMap.put(isbn, new ArrayList<>());
            }

            resultMap.get(isbn).add(toResultNoteDto(note));
        }

        result.setNotes(resultMap);
        return result;
    }

    public ResultNoteDto toResultNoteDto(Note note) {
        return new ResultNoteDto(note.getId(), note.getContent());
    }

}
