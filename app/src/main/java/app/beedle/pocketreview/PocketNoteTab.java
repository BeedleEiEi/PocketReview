package app.beedle.pocketreview;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.beedle.pocketreview.adapter.NoteAdapter;
import app.beedle.pocketreview.entity.NoteDatabase;
import app.beedle.pocketreview.entity.NoteEntity;
import app.beedle.pocketreview.model.Note;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.beedle.pocketreview.MainActivity.REQUEST_CODE;

public class PocketNoteTab extends AppCompatActivity implements NoteEntityItemClickListener {
    private static final int RESULT_UPDATE = 40;
    private List<NoteEntity> noteEntityList;
    private NoteEntity noteEntity;
    private NoteDatabase noteDatabase;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public final int RESULT_CODE = 10;
    private Button refresh;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 40) {
            System.out.println("INTHISSSS UPdate");
            loadNote();
        }
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pocket_note_tab);
        Toolbar tbMain = findViewById(R.id.tbMain);
        setSupportActionBar(tbMain);
        getSupportActionBar().setTitle("Pocket Review");
        refresh = findViewById(R.id.refresh);
        noteDatabase = Room.databaseBuilder(this, NoteDatabase.class, "NOTE").build();
        noteEntityList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoteEntityAdapter(this, noteEntityList);
        recyclerView.setAdapter(adapter);
        loadNote();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent addNoteIntent = new Intent(PocketNoteTab.this, AddNoteActivity.class);
                startActivityForResult(addNoteIntent, REQUEST_CODE);
                break;
            case R.id.action_remove_all_note:
                alertDialog();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_note_menu, menu);
        return true;
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


    private void alertDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirm Delete Note");
        alertDialog.setMessage("Do you want to delete this note?").setCancelable(true).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteNote();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        alertDialog.create();
        alertDialog.show();
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

    /*public void clearNote(View view) { //when click button
        deleteNote();
    }*/

    /*
    * When Item was clicked it's collect that object and put into pacelable
    *
    * */
    @Override
    public void onClickNoteEntityItem(NoteEntity noteEntity) {
        Intent intent = new Intent(this, EditNoteActivity.class);
        intent.putExtra("NoteInformation", noteEntity);
        //startActivity(intent);
        startActivityForResult(intent, RESULT_CODE);
    }

    public void reFresh(View view) {
        loadNote();
    }
}
