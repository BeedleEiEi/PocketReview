package app.beedle.pocketreview.model;

import java.util.List;

/**
 * Created by Beedle on 16/11/2560.
 */

public class Note {
    private String noteName;
    private String noteDesc;


    public Note(String noteName, String noteDesc) {
        this.setNoteName(noteName);
        this.setNoteDesc(noteDesc);
    }

    public Note() {

    }



    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteDesc() {
        return noteDesc;
    }

    public void setNoteDesc(String noteDesc) {
        this.noteDesc = noteDesc;
    }

}
