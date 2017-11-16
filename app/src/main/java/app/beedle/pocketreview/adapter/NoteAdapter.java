package app.beedle.pocketreview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.beedle.pocketreview.NoteItemClickListener;
import app.beedle.pocketreview.R;
import app.beedle.pocketreview.model.Note;

/**
 * Created by Beedle on 16/11/2560.
 */

public class NoteAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Note> noteList;
    private NoteItemClickListener noteItemClickListener;


    public NoteAdapter(Context context, List<Note> noteList, NoteItemClickListener noteItemClickListener) {
        this.context = context;
        this.noteList = noteList;
        this.noteItemClickListener = noteItemClickListener;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public NoteAdapter(Context context) {
        this.context = context;
    }

    //Base Implement

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View noteItemView = layoutInflater.inflate(R.layout.note_tab_item, null);

        TextView tvTitleName = noteItemView.findViewById(R.id.noteName);
        TextView tvDesc = noteItemView.findViewById(R.id.noteDescription);
        final Note index = noteList.get(position);
        tvTitleName.setText(index.getNoteName());
        tvDesc.setText(index.getNoteDesc());

        noteItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteItemClickListener.onNoteItemClick(index);
            }
        });
        return noteItemView;
    }
}

