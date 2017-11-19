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

public class EditNoteActivity extends AppCompatActivity {


    private NoteDatabase noteDatabase;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<NoteEntity> noteEntityList;
    private NoteEntity noteEntity;
    private Intent intent;

    private EditText titleName, description, detail, value;
    private String detailList;
    private Float priceList;
    private String tempDetail;
    String tempName = "";
    String tempDescription = "";
    String tempDet = "";
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


        noteDatabase = Room.databaseBuilder(this, NoteDatabase.class, "NOTE").build();
        noteEntityList = new ArrayList<>();
        noteEntity = new NoteEntity();
        intent = getIntent();
        //Get intent from Item selected contain NoteEntity Object in it
        //noteEntity = getIntent().getParcelableExtra("NoteInformation");//NoteEntity in this parcelable
        noteEntity = intent.getParcelableExtra("NoteInformation");//NoteEntity in this parcelable


        formatString(noteEntity);
        setDetail();

    }

    private void formatString(NoteEntity noteEntity) {
        //System.out.println(noteEntity.toString() + " >>> this is format");
        tempDetail = noteEntity.toString();
        String key = "";
        String test = "";
        int times = 1;
        tempDetail.matches(".*\\'[eNm]'\\b.*");
        for (int index = 0; index < tempDetail.length(); index++) {
            //System.out.println(tempDetail.charAt(index));
            if (tempDetail.charAt(index) == '[') {
                key += '[';
                System.out.println("INTHISSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
            }
            if (tempDetail.charAt(index) == 'e') {
                key += 'e';
            }
            if (tempDetail.charAt(index) == 'N') {
                key += 'N';
            }
            if (key == "[eN") {
                System.out.println("KEY IN DECISION");
                if (times == 1) {
                    tempName = tempDetail.replaceAll(".*\\b'[eNm]'\\b.*", "");
                } else if (times == 2) {
                    tempDescription = tempDetail.replaceAll(".*\\b'[eNd]'\\b.*", "");
                } else if (times == 3) {
                    tempDet = tempDetail.replaceAll(".*\\b'[eNde]'\\b.*", "");
                } else if (times == 4) {
                    tempPrice = tempDetail.replaceAll(".*\\b'[eNam]'\\b.*", "");
                }
                key = "";
            }
            test += tempDetail.charAt(index);
            times += 1;

        }
        /*tempName.replace("'[eNm]'", "");
        tempDescription.replace("'[eNd]'", "");
        tempDet.replace("'[eNde]'", "");
        tempPrice.replace("'[eNam]'", "");*/

    }


    private void setDetail() {
        //Set Text on screen
        System.out.println(intent.hasExtra("NoteInformation") + " Is it???");
        System.out.println(tempDet + " >>>>>>>>>>> Temp DET");


        titleName.setText(tempName);
        description.setText(tempDescription);
        detail.setText(tempDet);
        value.setText(tempPrice);


        //System.out.println(noteEntity.getName() + ">ASDASDASDSA");
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

    /*private void setAdapter(List<String> detailString) {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoteDetailAdapter(this, detailString);
        recyclerView.setAdapter(adapter);
    }*/
}
