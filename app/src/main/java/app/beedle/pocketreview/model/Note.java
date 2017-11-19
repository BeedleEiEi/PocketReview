package app.beedle.pocketreview.model;

import java.util.List;

/**
 * Created by Beedle on 16/11/2560.
 */

public class Note {
    private String noteName;
    private String noteDesc;

    public Note(String stringExtra, String stringExtra1, String stringExtra2) {
        this.noteName = stringExtra;
        this.noteDesc = stringExtra1;
        this.noteValue = stringExtra2;
    }

    public String getNoteValue() {
        return noteValue;
    }

    public void setNoteValue(String noteValue) {
        this.noteValue = noteValue;
    }

    private String noteValue;
    private OnNoteChangeListener onNoteChangeListener;

    public Note(String noteName, String noteDesc) {
        this.setNoteName(noteName);
        this.setNoteDesc(noteDesc);
    }

    public Note() {

    }

    public interface OnNoteChangeListener {
        void onNoteChangeListener(Note note);
    }

    public void setListener(OnNoteChangeListener onNoteChangeListener) {
        this.onNoteChangeListener = onNoteChangeListener;
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
