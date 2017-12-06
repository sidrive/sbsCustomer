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
import com.sabaindomedika.stscustomer.features.profile.adapter.EmployeeAdapter.ViewHolder;
import com.sabaindomedika.stscustomer.model.Profil.Support;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends Adapter<ViewHolder> {
  private Context mContext;
  private List<Support> mItem;
  public EmployeeAdapter(ArrayList<Support> items, Context context) {
    this.mContext = context;
    this.mItem = items;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View view = inflater.inflate(R.layout.list_item_employe, parent, false);
    ViewHolder holder = new ViewHolder(view);
    return holder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    Support checklistItem = getItem(position);
    holder.txtContent.setText(checklistItem.getName());
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
  public Support getItem(int position) {
    return mItem.get(position);
  }

  public void UpdateData(List<Support> listarray) {
    mItem = listarray;
    notifyDataSetChanged();
  }
}
