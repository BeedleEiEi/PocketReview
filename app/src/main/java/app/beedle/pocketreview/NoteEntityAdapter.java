package app.beedle.pocketreview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.beedle.pocketreview.entity.NoteEntity;

/**
 * Created by Beedle on 18/11/2560.
 */

public class NoteEntityAdapter extends RecyclerView.Adapter<NoteEntityAdapter.ViewHolder> {
    private List<NoteEntity> noteEntityList;
    private Context context;
    private ViewHolder viewHolder;

    public NoteEntityAdapter(Context context, List<NoteEntity> listNoteEntity) {
        this.context = context;
        this.noteEntityList = listNoteEntity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_tab_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NoteEntity noteEntity = noteEntityList.get(position);
        viewHolder.tvTitleName.setText(noteEntity.getName());
        viewHolder.tvDesc.setText(noteEntity.getDesc());
    }

    @Override
    public int getItemCount() {
        return noteEntityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitleName;
        public TextView tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitleName = itemView.findViewById(R.id.noteName);
            tvDesc = itemView.findViewById(R.id.noteDescription);

        }
    }


}
