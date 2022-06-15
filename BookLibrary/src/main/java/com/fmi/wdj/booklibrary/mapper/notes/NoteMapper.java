package com.fmi.wdj.booklibrary.mapper.notes;

import com.fmi.wdj.booklibrary.dto.notes.NoteDto;
import com.fmi.wdj.booklibrary.model.notes.Note;
import com.fmi.wdj.booklibrary.model.notes.NoteData;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NoteMapper {

    public NoteDto toNoteDto(NoteData noteData) {
        NoteDto result = new NoteDto();
        Map<String, List<String>> resultMap = new HashMap<>();

        int i = 0;
        for (Note note : noteData.getNotes()) {
            resultMap.merge(note.getBook().getISBN(),
                List.of(String.format("%d: %s", i++, note.getContent())),
                (l1, l2) -> {
                    l1.addAll(l2);
                    return l2;
                });
        }

        result.setNotes(resultMap);
        return result;
    }


}
