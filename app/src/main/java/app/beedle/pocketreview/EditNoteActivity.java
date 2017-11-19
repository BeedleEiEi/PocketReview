package app.beedle.pocketreview;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import app.beedle.pocketreview.adapter.NoteDetailAdapter;
import app.beedle.pocketreview.entity.NoteDatabase;
import app.beedle.pocketreview.entity.NoteEntity;
import app.beedle.pocketreview.model.NoteEntityDetail;
import butterknife.BindView;

public class EditNoteActivity extends AppCompatActivity {

    @BindView(R.id.tvTitleName)
    EditText titleName;

    @BindView(R.id.tvTripDesctiption)
    EditText description;

    @BindView(R.id.value)
    EditText value;

    @BindView(R.id.recycler_note_detail)
    RecyclerView recyclerView;

    private NoteDatabase noteDatabase;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<NoteEntity> noteEntityList;
    private NoteEntity noteEntity;
    private Intent intent;

    private String detailList;
    private Float priceList;
    private List<String> tempDetail;

    private NoteEntityDetail noteEntityDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        layoutManager = new LinearLayoutManager(this);

        noteDatabase = Room.databaseBuilder(this, NoteDatabase.class, "NOTE").build();
        noteEntityList = new ArrayList<>();
        noteEntity = new NoteEntity();

        //Get intent from Item selected contain NoteEntity Object in it
        noteEntity = getIntent().getParcelableExtra("NoteInformation");//NoteEntity in this parcelable
        System.out.println(noteEntity.getName() + ">>>>>>>>>>>>>>>");
        setDetail();

    }

    private void setDetail() {
        //Set Text on screen
        System.out.println(noteEntity.getName() + ">ASDASDASDSA");
        /*titleName.setText(noteEntity.getName()); //Set Trip name
        description.setText(noteEntity.getDesc()); // Set Description
        detailList = noteEntity.getDescList();
        priceList = noteEntity.getPriceList();*/

        /*if (detailList != null && priceList != null) {

            noteEntityDetail.setList(detailList, priceList);
            tempDetail = noteEntityDetail.getDetailAll(); // Get String contatinated
            setAdapter(tempDetail);
        }*/
    }

    private void setAdapter(List<String> detailString) {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoteDetailAdapter(this, detailString);
        recyclerView.setAdapter(adapter);
    }
}
