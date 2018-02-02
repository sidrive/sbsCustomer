package com.sabaindomedika.stscustomer.features.broadcast;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.model.broadcast.Data;
import com.sabaindomedika.stscustomer.model.broadcast.Datum;
import com.sabaindomedika.stscustomer.features.broadcast.BroadcastAdapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ikun on 02/02/18.
 */

public class BroadcastAdapter extends Adapter<ViewHolder> {
    private Context mContext;
    private List<Data> mItem;
    public BroadcastAdapter(ArrayList<Data> items, Context context) {
        this.mContext = context;
        this.mItem = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_broadcast, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data checklistItem = getItem(position);
        holder.txtTitle.setText(checklistItem.getTitle());
        holder.txtMessage.setText(checklistItem.getMessage());
    }


    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txtTitle)
        TextView txtTitle;

        @Bind(R.id.txtMessage)
        TextView txtMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Nullable
    public Data getItem(int position) {
        return mItem.get(position);
    }

    public void UpdateData(List<Data> listarray) {
        mItem = listarray;
        notifyDataSetChanged();
    }
}
