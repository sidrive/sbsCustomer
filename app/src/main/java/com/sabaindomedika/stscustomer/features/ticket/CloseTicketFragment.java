package com.sabaindomedika.stscustomer.features.ticket;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.basecommon.BaseDialogFragment;
import com.sabaindomedika.stscustomer.constant.StatusTicketCons;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.utils.Toasts;
import com.sabaindomedika.stscustomer.utils.helper.ErrorHelper;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Fajar Rianda on 14/05/2017.
 */
public class CloseTicketFragment extends BaseDialogFragment {

  public static final int DIALOG_REQUEST_CODE = 0x511;
  @Bind(R.id.btnSubmit) Button btnSubmit;
  @Bind(R.id.inpDescription) EditText inpDescription;
  @Bind(R.id.ratingBar) RatingBar ratingBar;
  @Inject ApiService apiService;
  String ticketId;
  int position;

  public static CloseTicketFragment newInstance(String ticketId, int position) {
    Bundle bundle = new Bundle();
    bundle.putString(String.class.getSimpleName(), ticketId);
    bundle.putInt(int.class.getSimpleName(), position);
    CloseTicketFragment closeTicketFragment = new CloseTicketFragment();
    closeTicketFragment.setArguments(bundle);
    return closeTicketFragment;
  }

  @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog = new Dialog(context);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(getContentView());
    return dialog;
  }

  @SuppressLint("InflateParams") private View getContentView() {
    View view = inflater.inflate(R.layout.fragment_close_ticket, null);
    ButterKnife.bind(this, view);
    ratingBar.setStepSize(0.5f);
    return view;
  }

  @Override public void onStart() {
    super.onStart();
    DaggerInit.networkComponent(context).inject(this);
    init();
  }

  private void init() {
    Bundle bundle = getArguments();
    ticketId = bundle.getString(String.class.getSimpleName());
    position = bundle.getInt(int.class.getSimpleName());
  }

  @OnClick(R.id.btnSubmit) public void closeTicket() {
    if (ratingBar.getRating() < 0.5) {
      Toasts.show("Silahkan memberi rating");
      return;
    }

    ProgressDialog dialog = new ProgressDialog(context);
    dialog.setMessage("Close Tiket...");
    dialog.show();
    Ticket ticket = new Ticket();
    ticket.setRating(ratingBar.getRating());
    ticket.setComment(inpDescription.getText().toString().trim());

    apiService.closeTicket(ticketId, ticket)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object -> {
          if (object.getData().getStatus().equalsIgnoreCase(StatusTicketCons.CLOSE)) {
            Toasts.show("Close Tiket Sukses");
            dialog.dismiss();
            dismiss();
          }
        }, error -> {
          dialog.dismiss();
          ErrorHelper.thrown(error);
        });
  }

  @Override public void dismiss() {
    Intent intent = new Intent().putExtra(int.class.getSimpleName(), position);
    getTargetFragment().onActivityResult(getTargetRequestCode(), getBaseActivity().RESULT_OK,
        intent);
    super.dismiss();
  }
}
