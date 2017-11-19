package com.sabaindomedika.stscustomer.features.notification;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseListAdapter;
import com.sabaindomedika.stscustomer.basecommon.BaseViewHolder;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.utils.Strings;

/**
 * Created by Fajar Rianda on 04/05/2017.
 */
public class NotificationAdapter extends BaseListAdapter<Ticket> {


  public NotificationAdapter(Context context) {
    super(context);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    NotificationViewHolder viewHolder;
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.list_item_ticket_status, parent, false);
      viewHolder = new NotificationViewHolder(convertView);
    } else {
      viewHolder = (NotificationViewHolder) convertView.getTag();
    }
    Ticket ticket = listData.get(position);
    viewHolder.txtTicketNumber.setText(ticket.getNumber());
    viewHolder.txtContent.setText(ticket.getDescription());
    viewHolder.txtDate.setText(Strings.getDate(ticket.getTimes().getDate()));
    viewHolder.txtStatusTicket.setText(ticket.getStatus());
    return convertView;
  }

  static class NotificationViewHolder extends BaseViewHolder {

    @Bind(R.id.txtTicketNumber) TextView txtTicketNumber;
    @Bind(R.id.txtStatusTicket) TextView txtStatusTicket;
    @Bind(R.id.imgAction) ImageView imgAction;
    @Bind(R.id.txtContent) TextView txtContent;
    @Bind(R.id.txtDate) TextView txtDate;

    public NotificationViewHolder(View view) {
      super(view);
    }
  }
}

