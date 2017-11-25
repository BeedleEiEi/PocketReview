package app.beedle.pocketreview.Activity;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import app.beedle.pocketreview.R;
import app.beedle.pocketreview.model.entity.NoteDatabase;
import app.beedle.pocketreview.model.entity.NoteEntity;

public class EditNoteActivity extends AppCompatActivity implements View.OnClickListener {


    private NoteDatabase noteDatabase;
    private RecyclerView.LayoutManager layoutManager;
    private static final int RESULT_UPDATE = 40;
    private String[] name = {"AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "GBP", "HKD", "HRK", "HUF", "IDR", "ILS", "INR", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "USD", "ZAR"};

    private List<NoteEntity> noteEntityList;
    private NoteEntity noteEntity;
    private Intent intent;
    private ImageButton deleteNote, ratingStar;
    private Button doneBtn;
    private TextView totalPrice, tvCurrencyName;
    private EditText titleName, description, detail, value;
    private float amount = 0;
    private int rating;
    private String nCurrency, tempDetail = "", tempName = "", tempDescription = "", tempPrice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        bindingID();
        setListenerDelete();
        setTempDetailEntity();
        setSpinnerItem();
        setDetail();
    }

    private void bindingID() {
        //Layout Inflate
        layoutManager = new LinearLayoutManager(this);
        titleName = findViewById(R.id.titleNameEditNote);
        description = findViewById(R.id.tvTripDesctiptionEditNote);
        detail = findViewById(R.id.detailEditNote);
        value = findViewById(R.id.valueEditNote);
        totalPrice = findViewById(R.id.totalPriceEdit);
        deleteNote = findViewById(R.id.deleteNote);
        ratingStar = findViewById(R.id.ratingStar);
        tvCurrencyName = findViewById(R.id.tvCurrencyName);
        doneBtn = findViewById(R.id.doneBtnEditNote);
    }

    private void setListenerDelete() {
        deleteNote.setOnClickListener(this);
    }

    private void setTempDetailEntity() {
        //Get DB
        noteDatabase = Room.databaseBuilder(this, NoteDatabase.class, "NOTE").build();
        //Initial Entity
        noteEntityList = new ArrayList<>();
        noteEntity = new NoteEntity();
        intent = getIntent();
        //Get intent from Item selected contain NoteEntity Object in it
        noteEntity = intent.getParcelableExtra("NoteInformation");//NoteEntity in this parcelable
        tempName = noteEntity.getName();
        tempDescription = noteEntity.getDesc();
        tempDetail = noteEntity.getDetail();
        tempPrice = noteEntity.getAmount();
        rating = noteEntity.getRating();
        nCurrency = noteEntity.getCurrency();
        tvCurrencyName.setText(noteEntity.getCurrency());
    }

    private void setSpinnerItem() {
        MaterialSpinner spinner = findViewById(R.id.spinner);
        spinner.setItems(name);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                nCurrency = item;
                tvCurrencyName.setText(item);
                noteEntity.setCurrency(item);
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_SHORT).show();
            }
        });
        //End Spinner
    }

    private void setDetail() {
        //Set Text on screen
        String[] text = tempPrice.split("\n");
        for (int i = 0; i < text.length; i++) {
            amount += Float.parseFloat(text[i]);
        }
        String nCurrency = noteEntity.getCurrency();
        titleName.setText(tempName);
        description.setText(tempDescription);
        detail.setText(tempDetail);
        value.setText(tempPrice);
        totalPrice.setText("TOTAL: " + amount + " " + nCurrency); //Can add Currency here
        setStar();

    }

    private void setStar() {
        int star;
        star = rating;
        ratingStar.setBackgroundColor(Color.parseColor("#FFD700"));
        switch (star) {
            case 1:
                ratingStar.setImageResource(R.drawable.one_star);
                break;
            case 2:
                ratingStar.setImageResource(R.drawable.two_star);
                break;
            case 3:
                ratingStar.setImageResource(R.drawable.three_star);
                break;
            case 4:
                ratingStar.setImageResource(R.drawable.four_star);
                break;
            case 5:
                ratingStar.setImageResource(R.drawable.five_star);
                break;
            default:
                break;

        }
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
        noteEntity.setRating(rating); //Set rating star
        noteEntity.setCurrency(nCurrency);
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

    public void setRatingStar(View view) {
        ratingDialog();
    }

    private void ratingDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final RatingBar ratingBar = new RatingBar(this);
        ratingBar.setNumStars(5);
        ratingBar.setMax(5);
        ratingBar.setRating(0);

        alertDialog.setIcon(android.R.drawable.btn_star_big_on);
        alertDialog.setTitle("Rating Trip!");
        alertDialog.setView(ratingBar);
        alertDialog.setCancelable(true).setNeutralButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ratingBar.setNumStars(5);
                int star = 0;
                star = ratingBar.getProgress();
                ratingStar.setBackgroundColor(Color.parseColor("#FFD700"));
                switch (star) {
                    case 1:
                        ratingStar.setImageResource(R.drawable.one_star);
                        break;
                    case 2:
                        ratingStar.setImageResource(R.drawable.two_star);
                        break;
                    case 3:
                        ratingStar.setImageResource(R.drawable.three_star);
                        break;
                    case 4:
                        ratingStar.setImageResource(R.drawable.four_star);
                        break;
                    case 5:
                        ratingStar.setImageResource(R.drawable.five_star);
                        break;
                    default:
                        break;

                }
                rating = star;
                dialog.dismiss();

            }

        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.create();
        alertDialog.show().getWindow().setLayout(700, 700);
    }

}
