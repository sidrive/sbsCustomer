package com.sabaindomedika.stscustomer.features.ticket.status.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Bind;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseFragment;
import com.sabaindomedika.stscustomer.basecommon.BaseListAdapter;
import com.sabaindomedika.stscustomer.basecommon.BaseViewHolder;
import com.sabaindomedika.stscustomer.features.ticket.CloseTicketFragment;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.utils.Strings;

/**
 * Created by Fajar Rianda on 04/05/2017.
 */
public class TicketStatusAdapter extends BaseListAdapter<Ticket> {

  BaseFragment fragment;

  public TicketStatusAdapter(Context context, BaseFragment fragment) {
    super(context);
    this.fragment = fragment;
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
    viewHolder.txtDate.setText(Strings.getDate(ticket.getTimes().getDate()));
    viewHolder.spnContent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int spinnerPosition, long id) {
        
        String ticketId = getItem(position).getId();
        if (spinnerPosition == 1) {
          CloseTicketFragment closeTicketFragment = CloseTicketFragment.newInstance(ticketId,position);
          closeTicketFragment.setTargetFragment(fragment,CloseTicketFragment.DIALOG_REQUEST_CODE);
          closeTicketFragment.show(fragment.getBaseFragmentManager(), CloseTicketFragment.class.getSimpleName());
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
    @Bind(R.id.txtDate) TextView txtDate;
    @Bind(R.id.spnContent) Spinner spnContent;

    public TicketStatusViewHolder(View view) {
      super(view);
    }
  }
}
