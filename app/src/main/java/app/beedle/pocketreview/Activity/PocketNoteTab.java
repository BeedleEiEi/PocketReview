package app.beedle.pocketreview.Activity;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.beedle.pocketreview.adapter.NoteEntityAdapter;
import app.beedle.pocketreview.listener.NoteEntityItemClickListener;
import app.beedle.pocketreview.R;
import app.beedle.pocketreview.model.entity.NoteDatabase;
import app.beedle.pocketreview.model.entity.NoteEntity;

public class PocketNoteTab extends AppCompatActivity implements NoteEntityItemClickListener {
    private List<NoteEntity> noteEntityList;
    private NoteDatabase noteDatabase;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public final int RESULT_CODE = 10;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 40) {
            loadNote();
        }
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocket_note_tab);
        setBinding();
        setToolbar();
        setDB();
        setView();
        loadNote();

    }

    private void setView() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoteEntityAdapter(this, noteEntityList);
        recyclerView.setAdapter(adapter);
    }

    private void setDB() {
        noteDatabase = Room.databaseBuilder(this, NoteDatabase.class, "NOTE").build();
        noteEntityList = new ArrayList<>();
    }

    private void setToolbar() {
        Toolbar tbMain = findViewById(R.id.tbPocket);
        setSupportActionBar(tbMain);
        getSupportActionBar().setTitle("Pocket Review");
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(navigationView);
    }

    private void setBinding() {
        drawerLayout = findViewById(R.id.drawer_pocket_tab);
        navigationView = findViewById(R.id.navigationView);
        recyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("Clicked !!!");
        int id = item.getItemId();
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            Toast.makeText(this, "Menu " + item.getTitle() + " has been selected", Toast.LENGTH_LONG).show();
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent addNoteIntent = new Intent(PocketNoteTab.this, AddNoteActivity.class);
                startActivityForResult(addNoteIntent, MainActivity.REQUEST_CODE);
                break;
            case R.id.action_remove_all_note:
                alertDialog();
                break;
        }

        Toast.makeText(this, "Menu " + item.getTitle() + " has been selected", Toast.LENGTH_LONG).show();

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

    private void setDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }

    public void selectItemDrawer(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case R.id.mainMenuTab:
                intent = new Intent(PocketNoteTab.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.currencyTabMenu:
                intent = new Intent(PocketNoteTab.this, CurrencyTab.class);
                startActivity(intent);
                break;
            case R.id.locationMenu:
                intent = new Intent(PocketNoteTab.this, LocationTab.class);
                startActivity(intent);
                break;
            default:
                //
        }
        item.setChecked(true);
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();
    }

    @Override
    public void onClickNoteEntityItem(NoteEntity noteEntity) {
        Intent intent = new Intent(this, EditNoteActivity.class);
        intent.putExtra("NoteInformation", noteEntity);
        startActivityForResult(intent, RESULT_CODE);
    }

}
