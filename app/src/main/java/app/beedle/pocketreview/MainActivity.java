package app.beedle.pocketreview;

import android.annotation.SuppressLint;
import android.app.LocalActivityManager;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.beedle.pocketreview.entity.NoteDatabase;
import app.beedle.pocketreview.entity.NoteEntity;
import app.beedle.pocketreview.model.Note;


@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {

    private TabHost host;
    LocalActivityManager mLocalActivityManager;
    private Note note;
    private List<Note> noteList;
    private String title;
    private String desc;
    private String value;
    private Button toNote;
    private Button toCurrency;
    static final int REQUEST_CODE = 1;
    private static final String DATABASE_NAME = "NoteDatabase";
    final NoteEntity noteEntity = new NoteEntity();
    private NoteDatabase noteDatabase;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_add:
                Intent addNoteIntent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(addNoteIntent, REQUEST_CODE);
                break;
            case R.id.action_setting:
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

        /*note.setNoteName(data.getStringExtra("note name"));
        note.setNoteDesc(data.getStringExtra("note desc"));
        note.setNoteValue(data.getStringExtra("note value"));
        noteList.add(note);
        System.out.println("In thisssss");*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);
        toNote = findViewById(R.id.toNoteBtn);
        toCurrency = findViewById(R.id.toCurrencyBtn);

        //host = findViewById(R.id.tabHost);
        //setTab();
        Toolbar tbMain = findViewById(R.id.tbMain);
        setSupportActionBar(tbMain);
        getSupportActionBar().setTitle("Pocket Review");
        noteDatabase = Room.databaseBuilder(this, NoteDatabase.class, "NOTE").build();


        //loadNote();

        //Create Empty Note , List Note
        noteList = new ArrayList<Note>();
        note = new Note();
    }

    private void setTab() {
        host.setup(mLocalActivityManager);

        //Tab 1
        TabHost.TabSpec spec2 = host.newTabSpec("Note").setIndicator("Note").setContent(new Intent(this, PocketNoteTab.class));
        host.addTab(spec2);

        //tab2
        TabHost.TabSpec spec = host.newTabSpec("Currency").setIndicator("Currency").setContent(new Intent(this, CurrencyTab.class));
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Location").setIndicator("Location").setContent(new Intent(this, LocationTab.class));
        host.addTab(spec);
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

/*    public void goCurrency(View view) { //Clicked Button
        Intent intent = new Intent(this, CurrencyTab.class);
        startActivity(intent);
    }*/

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

    public void gotoNote(View view) {
        Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
        startActivity(intent);
    }

    public void gotoCurrency(View view) {
        Intent intent = new Intent(MainActivity.this, CurrencyTab.class);
        startActivity(intent);
    }

   /* @Override
    public void onNoteChangeListener(List<Note> noteList) {

    }*/

}
