package app.beedle.pocketreview.model;

import java.util.List;

/**
 * Created by Beedle on 16/11/2560.
 */

public class NoteList {
    private List<Note> noteList;

    /*public NoteList(List<Note> noteList) {
        this.noteList = noteList;
    }*/

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    public void add(Note note) {
        this.noteList.add(note);
    }
}
