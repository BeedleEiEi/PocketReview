package app.beedle.pocketreview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import app.beedle.pocketreview.EditNoteActivity;
import app.beedle.pocketreview.NoteEntityAdapter;
import app.beedle.pocketreview.R;
import app.beedle.pocketreview.entity.NoteEntity;

/**
 * Created by Beedle on 19/11/2560.
 */

public class NoteDetailAdapter extends RecyclerView.Adapter<NoteDetailAdapter.ViewHolder> {

    private Context context;
    private ViewHolder viewHolder;
    private List<String> detailList;
    private NoteEntity noteEntity;
    private List<Float> priceList;
    private List<String> detailAll;

    public NoteDetailAdapter(Context context, List<String> detailList) {
        this.context = context;
        this.detailAll = detailList;
    }

    public NoteDetailAdapter(Context context, List<String> detailList, List<Float> priceList) {
        this.context = context;
        this.detailList = detailList;
        this.priceList = priceList;
    }

    public NoteDetailAdapter(EditNoteActivity context, NoteEntity noteEntities) {
        this.context = context;
        this.noteEntity = noteEntities;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_item_in_note, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String detail = detailAll.get(position);
        viewHolder.edDetail.setText(detail);
        viewHolder.edPrice.setText("1");
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public EditText edDetail;
        public EditText edPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            edDetail = itemView.findViewById(R.id.noteDescription);
            edPrice = itemView.findViewById(R.id.price);
        }
    }
}
