package com.sabaindomedika.stscustomer.features.ticket.status.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.features.ticket.status.adapter.PartAdapter.ViewHolder;
import com.sabaindomedika.stscustomer.model.part.Datum;
import java.util.ArrayList;
import java.util.List;

public class PartAdapter extends Adapter<ViewHolder> {

  private Context mContext;
  private List<Datum> mItem;

  public PartAdapter(ArrayList<Datum> items, Context context) {
    this.mContext = context;
    this.mItem = items;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View view = inflater.inflate(R.layout.item_list_part, parent, false);
    ViewHolder holder = new ViewHolder(view);
    return holder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    Datum part = getItem(position);
    holder.tvDesc.setText(part.getDescription());
    holder.tvQuantity.setText(part.getQuantity());
    holder.tvremarks.setText(part.getRemarks());
    holder.tvstatus.setText(part.getStatus());
    holder.tvTipeAlat.setText(part.getPartNumber());
  }

  @Override
  public int getItemCount() {
    return mItem.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tvTipeAlat)
    TextView tvTipeAlat;
    @Bind(R.id.tvQuantity)
    TextView tvQuantity;
    @Bind(R.id.tvDesc)
    TextView tvDesc;
    @Bind(R.id.tvremarks)
    TextView tvremarks;
    @Bind(R.id.tvstatus)
    TextView tvstatus;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  @Nullable
  public Datum getItem(int position) {
    return mItem.get(position);
  }

  public void UpdateData(List<Datum> listarray) {
    mItem = listarray;
    notifyDataSetChanged();
  }
}
