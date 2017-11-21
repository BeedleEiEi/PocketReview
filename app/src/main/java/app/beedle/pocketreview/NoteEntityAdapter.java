package app.beedle.pocketreview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
    private NoteEntityItemClickListener listener;


    public NoteEntityAdapter(Context context, List<NoteEntity> listNoteEntity) {
        this.context = context;
        this.noteEntityList = listNoteEntity;
        this.listener = (NoteEntityItemClickListener) context;
    }

    public void setListener(NoteEntityItemClickListener listener) {
        this.listener = listener;
    }

    public void setNoteEntityList(List<NoteEntity> noteEntityList) {
        this.noteEntityList = noteEntityList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_tab_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final NoteEntity noteEntity = noteEntityList.get(position);
        if (position % 2 == 0) {
            viewHolder.contentBlock.setBackgroundColor(Color.parseColor("#FF4500"));
        }
        viewHolder.tvTitleName.setText(noteEntity.getName());
        viewHolder.tvDesc.setText(noteEntity.getDesc());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(noteEntity.getName() + " >>>> FROM NOTE Adapter");

                listener.onClickNoteEntityItem(noteEntity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteEntityList.size();
    }


    //ViewHolder Class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitleName;
        public TextView tvDesc;
        public LinearLayout contentBlock;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitleName = itemView.findViewById(R.id.noteName);
            tvDesc = itemView.findViewById(R.id.noteDescription);
            contentBlock = itemView.findViewById(R.id.noteBlock);

        }
    }


}
