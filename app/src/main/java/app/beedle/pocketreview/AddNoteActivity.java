package app.beedle.pocketreview;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import app.beedle.pocketreview.entity.NoteDatabase;
import app.beedle.pocketreview.entity.NoteEntity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNoteActivity extends AppCompatActivity {
    @BindView(R.id.doneBtn)
    Button doneBtn;

    @BindView(R.id.tvTitleName)
    EditText titleName;

    @BindView(R.id.tvTripDesctiption)
    EditText desc;

    @BindView(R.id.detailAddNote)
    EditText detail;

    @BindView(R.id.valueAddNote)
    EditText value;

    NoteDatabase noteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);

        noteDatabase = Room.databaseBuilder(this, NoteDatabase.class, "NOTE").build();
    }

    private void insertNote(final NoteEntity noteEntity) {
        new AsyncTask<Void, Void, NoteEntity>() {
            @Override
            protected NoteEntity doInBackground(Void... voids) {
                noteDatabase.noteRoomDAO().insert(noteEntity);
                return null;
            }
        }.execute();
    }

    private NoteEntity saveNoteRecord() {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setName(titleName.getText().toString());
        noteEntity.setDesc(desc.getText().toString());
        noteEntity.setDesc(detail.getText().toString());
        noteEntity.setAmount(value.getText().toString());
        return noteEntity;
    }


    @OnClick(R.id.doneBtn)
    public void saveNoteAndGotoList() {
        NoteEntity noteEntity = saveNoteRecord();
        insertNote(noteEntity);
        gotoNoteList();
    }

    @OnClick(R.id.gotoListButton)
    public void gotoNoteList() {
        Intent intent = new Intent(AddNoteActivity.this, PocketNoteTab.class);
        startActivity(intent);
    }

}
