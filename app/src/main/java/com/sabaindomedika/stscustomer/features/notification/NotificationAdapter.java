package com.sabaindomedika.stscustomer.features.notification;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseListAdapter;
import com.sabaindomedika.stscustomer.basecommon.BaseViewHolder;
import com.sabaindomedika.stscustomer.model.Notification;

/**
 * Created by Fajar Rianda on 04/05/2017.
 */
public class NotificationAdapter extends BaseListAdapter<Notification> {
  public NotificationAdapter(Context context) {
    super(context);
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    NotificationViewHolder viewHolder;
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.list_item_notification, parent, false);
      viewHolder = new NotificationViewHolder(convertView);
    } else {
      viewHolder = (NotificationViewHolder) convertView.getTag();
    }
    Notification notification = listData.get(position);
    viewHolder.txtContent.setText(notification.getContent());
    return convertView;
  }

  static class NotificationViewHolder extends BaseViewHolder {

    @Bind(R.id.txtContent) TextView txtContent;

    public NotificationViewHolder(View view) {
      super(view);
    }
  }
}

