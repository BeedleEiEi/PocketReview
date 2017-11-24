package app.beedle.pocketreview;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

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

    @BindView(R.id.tvCurrencyName)
    TextView tvCurrency;

    @BindView(R.id.tvTripDesctiption)
    EditText desc;

    @BindView(R.id.detailAddNote)
    EditText detail;

    @BindView(R.id.valueAddNote)
    EditText value;

    NoteDatabase noteDatabase;

    private String[] name = {"AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "GBP", "HKD", "HRK", "HUF", "IDR", "ILS", "INR", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "USD", "ZAR"};
    private String nCurrency;

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

        //Spinner
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);

        spinner.setItems(name);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                nCurrency = item;
                tvCurrency.setText(item);
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_SHORT).show();
            }
        });

        //End Spinner

        Toolbar tbMain = findViewById(R.id.tbAddNote);
        setSupportActionBar(tbMain);
        getSupportActionBar().setTitle("Pocket Review");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbMain.setNavigationIcon(getResources().getDrawable(R.drawable.ic_navigate_before_black_24px));


        noteDatabase = Room.databaseBuilder(this, NoteDatabase.class, "NOTE").build();
    }


    @SuppressLint("StaticFieldLeak")
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
        addTotalprice(noteEntity); //Set total Price
        noteEntity.setRating(0);
        if (nCurrency == null || nCurrency.equals("currency")) {
            noteEntity.setCurrency("");
        } else {
            noteEntity.setCurrency(nCurrency);
        }
        return noteEntity;
    }

    private void addTotalprice(NoteEntity noteEntity) {
        String[] text = noteEntity.getAmount().split("\n");
        float amount = 0;

        for (int i = 0; i < text.length; i++) {
            amount += Float.parseFloat(text[i]);
        }
        noteEntity.setTotal(amount);
    }


    @OnClick(R.id.doneBtn)
    public void saveNoteAndGotoList() {
        NoteEntity noteEntity = saveNoteRecord();
        insertNote(noteEntity);
        gotoNoteList();
    }

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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
