package app.beedle.pocketreview;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import app.beedle.pocketreview.entity.NoteDatabase;
import app.beedle.pocketreview.entity.NoteEntity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.doneBtn)
    Button doneBtn;

    @BindView(R.id.deleteNote)
    ImageButton deleteBtn;

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
    public MenuInflater getMenuInflater() {
        return super.getMenuInflater();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);
        deleteBtn.setOnClickListener(this);
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
        noteEntity.setDetail(detail.getText().toString());
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

    private void alertDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirm Delete Note");
        alertDialog.setMessage("Do you want to delete this note?").setCancelable(true).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);*/
            }
        });
        alertDialog.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        System.out.println("Clicked Delete");
        alertDialog(); //Delete Alert
    }
}
