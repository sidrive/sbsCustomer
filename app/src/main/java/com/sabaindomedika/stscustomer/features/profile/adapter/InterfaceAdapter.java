package com.sabaindomedika.stscustomer.features.profile.adapter;

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
import com.sabaindomedika.stscustomer.features.profile.adapter.InterfaceAdapter.ViewHolder;
import com.sabaindomedika.stscustomer.model.profile.Datum_;
import com.sabaindomedika.stscustomer.model.profile.Datum__;
import java.util.ArrayList;
import java.util.List;

public class InterfaceAdapter extends Adapter<ViewHolder> {
  private Context mContext;
  private List<Datum__> mItem;
  public InterfaceAdapter(ArrayList<Datum__> items, Context context) {
    this.mContext = context;
    this.mItem = items;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View view = inflater.inflate(R.layout.list_item_interface, parent, false);
    ViewHolder holder = new ViewHolder(view);
    return holder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    Datum__ checklistItem = getItem(position);
    holder.txtContent.setText(checklistItem.getCode());
  }

  @Override
  public int getItemCount() {
    return mItem.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.txtContent)
    TextView txtContent;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  @Nullable
  public Datum__ getItem(int position) {
    return mItem.get(position);
  }
  public void UpdateData(List<Datum__> listarray) {
    mItem = listarray;
    notifyDataSetChanged();
  }
}