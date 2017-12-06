package com.sabaindomedika.stscustomer.features.notification;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseListAdapter;
import com.sabaindomedika.stscustomer.basecommon.BaseViewHolder;
import com.sabaindomedika.stscustomer.model.notification.Datum;

public class NotificationAdapter extends BaseListAdapter<Datum> {


  public NotificationAdapter(Context context) {
    super(context);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    NotificationViewHolder viewHolder;
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.list_item_ticket_notification, parent, false);
      viewHolder = new NotificationViewHolder(convertView);
    } else {
      viewHolder = (NotificationViewHolder) convertView.getTag();
    }
    Datum notification = listData.get(position);
    viewHolder.txtTicketNumber.setText(notification.getTicket().getData().getNumber());
    viewHolder.txtContent.setText(notification.getTicket().getData().getComment());
    viewHolder.txtDate.setText(notification.getTicket().getData().getCreatedAt().getDate());
    viewHolder.txtStatusTicket.setText(notification.getBody());
    if (notification.getIsRead().equals(false)){
      viewHolder.imgNotify.setVisibility(View.VISIBLE);
      viewHolder.lytTicket.setBackgroundResource(R.color.colorBlue);
    } else {
      viewHolder.imgNotify.setVisibility(View.GONE);
      viewHolder.lytTicket.setBackgroundColor(Color.WHITE);
    }
    return convertView;
  }

  static class NotificationViewHolder extends BaseViewHolder {

    @Bind(R.id.txtTicketNumber) TextView txtTicketNumber;
    @Bind(R.id.txtStatusTicket) TextView txtStatusTicket;
    @Bind(R.id.txtContent) TextView txtContent;
    @Bind(R.id.txtDate) TextView txtDate;
    @Bind(R.id.imgNotify) ImageView imgNotify;
    @Bind(R.id.lytTicket) LinearLayout lytTicket;

    public NotificationViewHolder(View view) {
      super(view);
    }
  }
}

