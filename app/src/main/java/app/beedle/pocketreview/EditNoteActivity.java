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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.beedle.pocketreview.adapter.NoteDetailAdapter;
import app.beedle.pocketreview.entity.NoteDatabase;
import app.beedle.pocketreview.entity.NoteEntity;
import app.beedle.pocketreview.model.NoteEntityDetail;

public class EditNoteActivity extends AppCompatActivity implements View.OnClickListener {


    private NoteDatabase noteDatabase;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final int RESULT_UPDATE = 40;

    private List<NoteEntity> noteEntityList;
    private NoteEntity noteEntity;
    private Intent intent;
    private ImageButton deleteNote;
    private Button doneBtn;
    private TextView totalPrice;
    private EditText titleName, description, detail, value;
    private float amount = 0;
    String tempDetail = "";
    String tempName = "";
    String tempDescription = "";
    String tempPrice = "";
    private NoteEntityDetail noteEntityDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        layoutManager = new LinearLayoutManager(this);
        titleName = findViewById(R.id.titleNameEditNote);
        description = findViewById(R.id.tvTripDesctiptionEditNote);
        detail = findViewById(R.id.detailEditNote);
        value = findViewById(R.id.valueEditNote);
        totalPrice = findViewById(R.id.totalPriceEdit);
        deleteNote = findViewById(R.id.deleteNote);
        doneBtn = findViewById(R.id.doneBtnEditNote);

        deleteNote.setOnClickListener(this);
        noteDatabase = Room.databaseBuilder(this, NoteDatabase.class, "NOTE").build();
        noteEntityList = new ArrayList<>();
        noteEntity = new NoteEntity();
        intent = getIntent();
        //Get intent from Item selected contain NoteEntity Object in it
        noteEntity = intent.getParcelableExtra("NoteInformation");//NoteEntity in this parcelable


        tempName = noteEntity.getName();
        tempDescription = noteEntity.getDesc();
        tempDetail = noteEntity.getDetail();
        tempPrice = noteEntity.getAmount();


        String[] text = tempPrice.split("\n");

        System.out.println("------------PRICE LOOP-------------");
        for (int i = 0; i < text.length; i++) {
            amount += Float.parseFloat(text[i]);
        }

        System.out.println(amount + " <<<<<<<< Amount");
        System.out.println("---------------------------------");
        System.out.println(tempName);
        System.out.println(tempDescription);
        System.out.println(tempDetail);
        System.out.println(tempPrice);
        System.out.println("---------------------------------");

        setDetail();
    }


    private void setDetail() {
        //Set Text on screen

        titleName.setText(tempName);
        description.setText(tempDescription);
        detail.setText(tempDetail);
        value.setText(tempPrice);
        totalPrice.setText(amount + ""); //Can add Currency here

    }


    @SuppressLint("StaticFieldLeak")
    private void deleteNote() {
        new AsyncTask<Void, Void, List<NoteEntity>>() {
            @Override
            protected List<NoteEntity> doInBackground(Void... voids) {
                noteDatabase.noteRoomDAO().deleteNoteRecord(noteEntity);
                return null;
            }

            @Override
            protected void onPostExecute(List<NoteEntity> noteEntityList) {
                Intent intent = new Intent(EditNoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }.execute();

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
    private void UpdateNote(final NoteEntity noteEntity) {
        new AsyncTask<Void, Void, NoteEntity>() {
            @Override
            protected NoteEntity doInBackground(Void... voids) {
                noteDatabase.noteRoomDAO().update(noteEntity);
                return null;
            }

            @Override
            protected void onPostExecute(NoteEntity noteEntity) {
                Toast.makeText(EditNoteActivity.this, "Note has been Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditNoteActivity.this, PocketNoteTab.class);
                startActivityForResult(intent, RESULT_UPDATE);
            }
        }.execute();
    }

    private NoteEntity saveNoteRecord() {
        noteEntity.setName(titleName.getText().toString());
        noteEntity.setDesc(description.getText().toString());
        noteEntity.setDetail(detail.getText().toString());
        noteEntity.setAmount(value.getText().toString());
        noteEntity.setTotal(amount); // Set total price
        return noteEntity;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.deleteNote) {
            alertDialog();
        }
    }

    public void updateNote(View view) {
        noteEntity = saveNoteRecord();
        UpdateNote(noteEntity);
    }
}
