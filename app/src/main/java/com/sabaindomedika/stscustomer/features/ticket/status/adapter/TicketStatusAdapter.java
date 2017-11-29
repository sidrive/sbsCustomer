package com.sabaindomedika.stscustomer.features.ticket.status.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.basecommon.BaseFragment;
import com.sabaindomedika.stscustomer.basecommon.BaseListAdapter;
import com.sabaindomedika.stscustomer.basecommon.BaseViewHolder;
import com.sabaindomedika.stscustomer.constant.StatusTicketCons;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.features.ticket.CloseTicketFragment;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.utils.Strings;
import com.sabaindomedika.stscustomer.utils.helper.ErrorHelper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Fajar Rianda on 04/05/2017.
 */
public class TicketStatusAdapter extends BaseListAdapter<Ticket> {

  BaseFragment fragment;
  @Inject ApiService apiService;
  boolean isHistory;

  public TicketStatusAdapter(Context context, BaseFragment fragment, boolean isHistory) {
    super(context);
    this.fragment = fragment;
    this.isHistory = isHistory;
    DaggerInit.networkComponent(context).inject(this);
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
    viewHolder.txtTicketNumber.setText(ticket.getNumber());
    viewHolder.txtContent.setText(ticket.getDescription());
    viewHolder.txtDate.setText(Strings.getDate(ticket.getTimes().getDate()));
    viewHolder.txtStatusTicket.setText(ticket.getStatus());

    if (isHistory) {
      RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewHolder.txtStatusTicket.getLayoutParams();
      params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
      params.addRule(RelativeLayout.ALIGN_PARENT_END);
    }
    viewHolder.imgAction.setVisibility(View.INVISIBLE);

    /*viewHolder.imgAction.setOnClickListener(v -> showAction(ticket, position));*/
    return convertView;
  }

  /*private void showAction(Ticket ticket, int position) {
    List<String> itemAction = new ArrayList<>();

    if (ticket.getStatus().equalsIgnoreCase(StatusTicketCons.NEW)) {
      itemAction.add("Cancel");
    }else if (ticket.getStatus().equalsIgnoreCase(StatusTicketCons.CONFIRM)){
      itemAction.add("Cancel");
    }else if (ticket.getStatus().equalsIgnoreCase(StatusTicketCons.DONE)) {
      itemAction.add("Close");
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setItems(itemAction.toArray(new String[itemAction.size()]), (dialog, index) -> {
      switch (itemAction.get(0)) {
        case "Cancel":
          AlertDialog.Builder cancelDialogBuilder = new AlertDialog.Builder(context);
          cancelDialogBuilder.setMessage("Anda yakin membatalkan tiket ini ?");
          cancelDialogBuilder.setPositiveButton("Ya", (dialog1, which) -> {
            cancelTicket(ticket.getId(), position);
          });
          cancelDialogBuilder.setNegativeButton("Tidak", (dialog1, which) -> {
            dialog1.dismiss();
          });
          AlertDialog cancelDialog = cancelDialogBuilder.create();
          cancelDialog.show();
          break;
        case "Close":
          CloseTicketFragment closeTicketFragment =
              CloseTicketFragment.newInstance(ticket.getId());
          closeTicketFragment.setTargetFragment(fragment, CloseTicketFragment.DIALOG_REQUEST_CODE);
          closeTicketFragment.show(fragment.getBaseFragmentManager(),
              CloseTicketFragment.class.getSimpleName());
          break;
      }
    });
    AlertDialog dialog = builder.create();
    dialog.show();
  }*/

  private void cancelTicket(String id, int position) {
    ProgressDialog dialog = new ProgressDialog(context);
    dialog.setMessage("Cancel Tiket...");
    dialog.show();
    apiService.cancelTicket(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object -> {
          if (object.getData().getStatus().equalsIgnoreCase(StatusTicketCons.CANCEL)) {
            dialog.dismiss();
            remove(position);
          }
        }, error -> {
          dialog.dismiss();
          ErrorHelper.thrown(error);
        });
  }

  static class TicketStatusViewHolder extends BaseViewHolder {

    @Bind(R.id.txtTicketNumber) TextView txtTicketNumber;
    @Bind(R.id.txtContent) TextView txtContent;
    @Bind(R.id.txtStatusTicket) TextView txtStatusTicket;
    @Bind(R.id.txtDate) TextView txtDate;
    @Bind(R.id.imgAction) ImageView imgAction;

    public TicketStatusViewHolder(View view) {
      super(view);
    }
  }
}
