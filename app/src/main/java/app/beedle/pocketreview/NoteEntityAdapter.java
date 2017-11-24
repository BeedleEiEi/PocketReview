package app.beedle.pocketreview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
            viewHolder.contentBlock.setBackgroundColor(Color.parseColor("#FFE4E1"));
        }
        viewHolder.tvTitleName.setText(noteEntity.getName());
        viewHolder.tvDesc.setText(noteEntity.getDesc());
        viewHolder.tvTotalAmount.setText(noteEntity.getTotal().toString() + " " + noteEntity.getCurrency());
        selectImageStar(noteEntity);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(noteEntity.getName() + " >>>> FROM NOTE Adapter");

                listener.onClickNoteEntityItem(noteEntity);
            }
        });
    }

    private void selectImageStar(NoteEntity noteEntity) {
        int star = 0;
        star = noteEntity.getRating();
        viewHolder.starButton.setBackgroundColor(Color.parseColor("#FFD700"));
        switch (star) {
            case 1:
                viewHolder.starButton.setImageResource(R.drawable.one_star);
                break;
            case 2:
                viewHolder.starButton.setImageResource(R.drawable.two_star);
                break;
            case 3:
                viewHolder.starButton.setImageResource(R.drawable.three_star);
                break;
            case 4:
                viewHolder.starButton.setImageResource(R.drawable.four_star);
                break;
            case 5:
                viewHolder.starButton.setImageResource(R.drawable.five_star);
                break;
            default:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return noteEntityList.size();
    }


    //ViewHolder Class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitleName;
        public TextView tvDesc;
        public TextView tvTotalAmount;
        public ImageButton starButton;
        public LinearLayout contentBlock;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitleName = itemView.findViewById(R.id.noteName);
            tvDesc = itemView.findViewById(R.id.noteDescription);
            tvTotalAmount = itemView.findViewById(R.id.totalPrice);
            contentBlock = itemView.findViewById(R.id.noteBlock);
            starButton = itemView.findViewById(R.id.ratingNoteList);

        }
    }


}
