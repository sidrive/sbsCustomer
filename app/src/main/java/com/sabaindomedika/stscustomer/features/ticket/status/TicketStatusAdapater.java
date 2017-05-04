package com.sabaindomedika.stscustomer.features.ticket.status;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseListAdapter;
import com.sabaindomedika.stscustomer.basecommon.BaseViewHolder;
import com.sabaindomedika.stscustomer.model.Ticket;

/**
 * Created by Fajar Rianda on 04/05/2017.
 */
public class TicketStatusAdapater extends BaseListAdapter<Ticket> {

  public TicketStatusAdapater(Context context) {
    super(context);
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    TicketStatusViewHolder viewHolder;
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.list_item_ticket_status, parent, false);
      viewHolder = new TicketStatusViewHolder(convertView);
    } else {
      viewHolder = (TicketStatusViewHolder) convertView.getTag();
    }
    Ticket ticket = listData.get(position);
    viewHolder.txtTicketNumber.setText(ticket.getTicketNumber());
    viewHolder.txtContent.setText(ticket.getContent());
    return convertView;
  }

  static class TicketStatusViewHolder extends BaseViewHolder {

    @Bind(R.id.txtTicketNumber) TextView txtTicketNumber;
    @Bind(R.id.txtContent) TextView txtContent;

    public TicketStatusViewHolder(View view) {
      super(view);
    }
  }
}
