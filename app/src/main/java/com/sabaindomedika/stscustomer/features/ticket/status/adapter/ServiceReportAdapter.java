package com.sabaindomedika.stscustomer.features.ticket.status.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.features.ticket.status.adapter.ServiceReportAdapter.ViewHolder;
import com.sabaindomedika.stscustomer.model.servicereport.Datum;
import java.util.ArrayList;
import java.util.List;

public class ServiceReportAdapter extends Adapter<ViewHolder> {

  private Context mContext;
  private List<Datum> mItem;
  private onClickButton onclickbutton;

  public ServiceReportAdapter(ArrayList<Datum> items, Context context,
      onClickButton onclickbutton) {
    this.mContext = context;
    this.mItem = items;
    this.onclickbutton = onclickbutton;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View view = inflater.inflate(R.layout.list_item_ticket_servicereport, parent, false);
    ViewHolder holder = new ViewHolder(view, this.onclickbutton);
    return holder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    Datum servicereport = getItem(position);
    holder.txtFaultDescription.setText(servicereport.getFaultDescription());
    holder.txtNumber.setText(servicereport.getName());
    holder.txtProblem.setText(servicereport.getProblem());
    holder.txtSolution.setText(servicereport.getSolution());
    holder.txtPart.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
      onclickbutton.onClickListener(servicereport.getId());
      }
    });
  }

  @Override
  public int getItemCount() {
    return mItem.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    onClickButton onclickbutton;
    @Bind(R.id.txtNumber)
    TextView txtNumber;
    @Bind(R.id.txtProblem)
    TextView txtProblem;
    @Bind(R.id.txtFaultDescription)
    TextView txtFaultDescription;
    @Bind(R.id.txtSolution)
    TextView txtSolution;
    @Bind(R.id.txtPart)
    TextView txtPart;

    public ViewHolder(View itemView, onClickButton onclickbutton) {
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
  public interface onClickButton {
    void onClickListener(int id);
  }
}
