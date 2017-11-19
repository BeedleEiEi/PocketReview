package app.beedle.pocketreview;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.beedle.pocketreview.adapter.NoteAdapter;
import app.beedle.pocketreview.model.Note;
import app.beedle.pocketreview.model.NoteList;

public class PocketNoteTab extends Activity implements NoteItemClickListener {
    private ListView lvNote; //listview note
    private Note note;
    private List<Note> listNoteSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocket_note_tab);
        lvNote = findViewById(R.id.pocket_noteList);
        NoteAdapter noteAdapter = new NoteAdapter(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        listNoteSend = new ArrayList<Note>(); //Initial list
        note = new Note("Test", "Description"); //Create note
        lvNote.setAdapter(new NoteAdapter(this, listNoteSend, this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        //get note list  here ....

    }

    @Override
    public void onNoteItemClick(Note n) {

    }


}
