package com.fmi.wdj.booklibrary.model.notes;

import com.fmi.wdj.booklibrary.model.user.User;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "notes")
public class NoteData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User owner;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "note_id")
    private List<Note> notes;

    public NoteData() {
    }

    public NoteData(User owner, List<Note> notes) {
        this.owner = owner;
        this.notes = notes;
    }
}
