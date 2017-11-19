package app.beedle.pocketreview;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.beedle.pocketreview.adapter.NoteAdapter;
import app.beedle.pocketreview.entity.NoteDatabase;
import app.beedle.pocketreview.entity.NoteEntity;
import app.beedle.pocketreview.model.Note;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PocketNoteTab extends AppCompatActivity implements NoteItemClickListener {
    private ListView lvNote; //listview note
    private Note note;
    private List<Note> listNoteSend;
    private List<NoteEntity> noteEntityList;
    private NoteEntity noteEntity;
    private NoteDatabase noteDatabase;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    /*private static final String DATABASE_NAME = "NoteDatabase";
    final NoteEntity noteEntity = new NoteEntity();
    private NoteDatabase noteDatabase;*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pocket_note_tab);
<<<<<<< HEAD
        lvNote = findViewById(R.id.pocket_noteList);
        NoteAdapter noteAdapter = new NoteAdapter(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        listNoteSend = new ArrayList<Note>(); //Initial list
        note = new Note("Test", "Description"); //Create note
        lvNote.setAdapter(new NoteAdapter(this, listNoteSend, this));
=======
        //noteDatabase = Room.databaseBuilder(getApplicationContext(), NoteDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        noteDatabase = Room.databaseBuilder(this, NoteDatabase.class, "NOTE").build();


        //lvNote = findViewById(R.id.pocket_noteList);
        //NoteAdapter noteAdapter = new NoteAdapter(this, listNoteSend);
        /*NoteAdapter noteAdapter = new NoteAdapter(this, noteEntityList);
        LayoutInflater inflater = LayoutInflater.from(this);*/
        noteEntityList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoteEntityAdapter(this, noteEntityList);
        recyclerView.setAdapter(adapter);

        loadNote();
>>>>>>> addDB

    }

    @SuppressLint("StaticFieldLeak")
    private void loadNote() {
        new AsyncTask<Void, Void, List<NoteEntity>>() {
            @Override
            protected List<NoteEntity> doInBackground(Void... voids) {
                List<NoteEntity> result = noteDatabase.noteRoomDAO().getAll();
                noteEntityList = result;
                return result;
            }

            @Override
            protected void onPostExecute(List<NoteEntity> noteEntities) {
                setAdapterNote(noteEntities);
            }
        }.execute();
    }

    private void setAdapterNote(List<NoteEntity> noteEntityList) {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoteEntityAdapter(this, noteEntityList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //get note list  here ....

    }

    @Override
    public void onNoteItemClick(Note n) {

    }

    @SuppressLint("StaticFieldLeak")
    private void deleteNote() {
        new AsyncTask<Void, Void, List<NoteEntity>>() {
            @Override
            protected List<NoteEntity> doInBackground(Void... voids) {
                noteDatabase.noteRoomDAO().deleteNoteAll(noteEntityList);
                return null;
            }

            @Override
            protected void onPostExecute(List<NoteEntity> noteEntityList) {
                List<NoteEntity> noteEntityList1 = new ArrayList<>();
                setAdapterNote(noteEntityList1);
            }
        }.execute();

    }

    public void clearNote(View view) {
        deleteNote();

    }
}
