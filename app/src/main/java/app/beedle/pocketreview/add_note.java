package app.beedle.pocketreview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.beedle.pocketreview.entity.NoteDatabase;
import app.beedle.pocketreview.entity.NoteEntity;
import app.beedle.pocketreview.model.Note;

public class add_note extends AppCompatActivity implements View.OnClickListener {
    private EditText titleName;
    private EditText detail;
    private EditText value;
    private Button doneBtn;
    private List<Note> noteList;
    private Note note;
    static final int REQUEST_CODE_2 = 2;

    private NoteEntity noteEntity;
    NoteDatabase noteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        titleName = findViewById(R.id.titleName);
        detail = findViewById(R.id.detail);
        value = findViewById(R.id.value);
        doneBtn = findViewById(R.id.doneBtn);
        noteList = new ArrayList<Note>();
        note = new Note();
        noteEntity = new NoteEntity();
        noteDatabase = Room.databaseBuilder(this, NoteDatabase.class, "NOTE").build();


        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(add_note.this, PocketNoteTab.class);
                noteEntity.setName(titleName.getText().toString());
                noteEntity.setDesc(detail.getText().toString());
                noteEntity.setAmount(Float.parseFloat(value.getText().toString()));
                insertNote();
                startActivity(intent);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void insertNote() {
        new AsyncTask<Void, Void, NoteEntity>() {
            @Override
            protected NoteEntity doInBackground(Void... voids) {
                noteDatabase.noteRoomDAO().insert(noteEntity);
                return null;
            }
        }.execute();
    }

    private void finishActivity() {

        Intent output = new Intent();
        setResult(RESULT_OK, output);
        finish();
    }


    @Override
    public void onClick(View v) {
        if (detail.getText().length() == 0 || value.getText().toString().length() == 0) {
            Toast.makeText(this, "Please enter in this field.",
                    Toast.LENGTH_LONG).show();
        } /*else {
            insertNote();
            finishActivity();
        }*/
    }
}
