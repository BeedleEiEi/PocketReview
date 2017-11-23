package app.beedle.pocketreview;

import android.annotation.SuppressLint;

import android.app.LocalActivityManager;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.beedle.pocketreview.entity.NoteDatabase;
import app.beedle.pocketreview.entity.NoteEntity;
import app.beedle.pocketreview.model.Note;


@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {

    LocalActivityManager mLocalActivityManager;
    private Note note;
    private List<Note> noteList;
    static final int REQUEST_CODE = 1;
    private NoteDatabase noteDatabase;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent addNoteIntent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(addNoteIntent, REQUEST_CODE);
                break;
        }

        Toast.makeText(this, "Menu " + item.getTitle() + " has been selected", Toast.LENGTH_LONG).show();

        return true;
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            loadNote();
            System.out.println("request complete");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        Toolbar tbMain = findViewById(R.id.tbMain);
        setSupportActionBar(tbMain);
        getSupportActionBar().setTitle("Pocket Review");

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(navigationView);
        noteDatabase = Room.databaseBuilder(this, NoteDatabase.class, "NOTE").build();

        noteList = new ArrayList<>();
        note = new Note();
    }

    public void selectItemDrawer(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case R.id.pocketTabMenu:
                intent = new Intent(MainActivity.this, PocketNoteTab.class);
                startActivity(intent);
                break;
            case R.id.currencyTabMenu:
                intent = new Intent(MainActivity.this, CurrencyTab.class);
                startActivity(intent);
                break;
            case R.id.locationMenu:
                intent = new Intent(MainActivity.this, LocationTab.class);
                startActivity(intent);
                break;
            default:
                //
        }
        item.setChecked(true);
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();
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

    @SuppressLint("StaticFieldLeak")
    private void loadNote() {
        new AsyncTask<Void, Void, List<NoteEntity>>() {
            @Override
            protected List<NoteEntity> doInBackground(Void... voids) {
                List<NoteEntity> result = noteDatabase.noteRoomDAO().getAll();
                return result;
            }

            @Override
            protected void onPostExecute(List<NoteEntity> noteEntities) {
                Intent intent = new Intent(MainActivity.this, PocketNoteTab.class);
                startActivity(intent);
            }
        }.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocalActivityManager.dispatchPause(!isFinishing());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocalActivityManager.dispatchResume();
    }


}
