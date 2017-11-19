package app.beedle.pocketreview.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.beedle.pocketreview.NoteItemClickListener;
import app.beedle.pocketreview.PocketNoteTab;
import app.beedle.pocketreview.R;
import app.beedle.pocketreview.entity.NoteDatabase;
import app.beedle.pocketreview.entity.NoteEntity;
import app.beedle.pocketreview.model.Note;

/**
 * Created by Beedle on 16/11/2560.
 */

public class NoteAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Note> noteList;
    private NoteItemClickListener noteItemClickListener;
    private List<NoteEntity> noteEntityList;
    private NoteEntity noteEntity;


    public NoteAdapter(Context context) {
        this.context = context;

    }

    public NoteAdapter(Context applicationContext, List<NoteEntity> noteEntities) {
        this.context = applicationContext;
        this.noteEntityList = noteEntities;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Base Implement
    @Override
    public int getCount() {
        return noteEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        @SuppressLint("ViewHolder") View noteItemView = layoutInflater.inflate(R.layout.note_tab_item, null);
        TextView tvTitleName = noteItemView.findViewById(R.id.noteName);
        TextView tvDesc = noteItemView.findViewById(R.id.noteDescription);
        NoteEntity index = noteEntityList.get(position);
        tvTitleName.setText(index.getName());
        tvDesc.setText(index.getDesc());

        return noteItemView;

    }
}

