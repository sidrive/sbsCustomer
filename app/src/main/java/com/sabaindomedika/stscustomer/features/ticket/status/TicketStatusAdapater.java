package com.sabaindomedika.stscustomer.features.ticket.status;

import android.app.FragmentManager;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Bind;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseListAdapter;
import com.sabaindomedika.stscustomer.basecommon.BaseViewHolder;
import com.sabaindomedika.stscustomer.features.ticket.CloseTicketFragment;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.model.TicketOld;

/**
 * Created by Fajar Rianda on 04/05/2017.
 */
public class TicketStatusAdapater extends BaseListAdapter<Ticket> {

  FragmentManager fragmentManager;

  public TicketStatusAdapater(Context context, FragmentManager fragmentManager) {
    super(context);
    this.fragmentManager = fragmentManager;
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
    viewHolder.txtTicketId.setText(ticket.getId());
    viewHolder.txtContent.setText(ticket.getDescription());
    viewHolder.spnContent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String ticketId = getItem(position).getId();
        if (position == 1){
          CloseTicketFragment closeTicketFragment = CloseTicketFragment.newInstance();
          closeTicketFragment.show(fragmentManager, CloseTicketFragment.class.getSimpleName());
        }

      }

      @Override public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    return convertView;
  }

  static class TicketStatusViewHolder extends BaseViewHolder {

    @Bind(R.id.txtTicketId) TextView txtTicketId;
    @Bind(R.id.txtContent) TextView txtContent;
    @Bind(R.id.spnContent) Spinner spnContent;

    public TicketStatusViewHolder(View view) {
      super(view);
    }
  }
}
