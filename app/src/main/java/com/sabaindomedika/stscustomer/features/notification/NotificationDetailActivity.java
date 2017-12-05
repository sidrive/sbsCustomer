package com.sabaindomedika.stscustomer.features.notification;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;
import com.sabaindomedika.stscustomer.constant.StatusTicketCons;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.features.ticket.CloseTicketFragment;
import com.sabaindomedika.stscustomer.features.ticket.status.TicketStatusActivity;
import com.sabaindomedika.stscustomer.model.notification.Data;
import com.sabaindomedika.stscustomer.model.notification.Datum;
import com.sabaindomedika.stscustomer.utils.helper.ErrorHelper;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NotificationDetailActivity extends BaseActivity {

  int id_ticket;
  @Inject
  ApiService apiService;
  @Bind(R.id.toolbar)
  Toolbar toolbar;
  @Bind(R.id.txtTicketNumber)
  TextView txtTicketNumber;
  @Bind(R.id.txtDate)
  TextView txtDate;
  @Bind(R.id.txtTicketType)
  TextView txtTicketType;
  @Bind(R.id.txtName)
  TextView txtName;
  @Bind(R.id.txtPhoneNumber)
  TextView txtPhoneNumber;
  @Bind(R.id.txtDescription)
  TextView txtDescription;
  @Bind(R.id.btncancel)
  Button btncancel;
  @Bind(R.id.btnclose)
  Button btnclose;

  public static void start(Context context, Datum datum) {
    Bundle bundle = new Bundle();
    bundle.putParcelable(Datum.class.getSimpleName(), datum);
    bundle.putParcelable(Data.class.getSimpleName(), datum.getTicket().getData());
    Intent intent = new Intent(context, NotificationDetailActivity.class);
    intent.putExtras(bundle);
    context.startActivity(intent);
  }

  @Override
  protected void onStart() {
    super.onStart();
    DaggerInit.networkComponent(this).inject(this);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ticket_status_detail);
    ButterKnife.bind(this);
    init();
    setupToolbar();
  }

  private void init() {
    Datum datum = getIntent().getExtras().getParcelable(Datum.class.getSimpleName());
    id_ticket = datum.getTicket().getData().getId();
    Log.e("init", "TicketStatusDetailActivity" + id_ticket);
    showContent(datum);
  }

  @OnClick(R.id.btncancel)
  public void onCancel() {
    Builder cancelDialogBuilder = new Builder(this);
    cancelDialogBuilder.setMessage("Anda yakin membatalkan tiket ini ?");
    cancelDialogBuilder.setPositiveButton("Ya", (dialog1, which) -> {
      apiService.cancelTicket(String.valueOf(id_ticket))
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(object -> {
            if (object.getData().getStatus().equalsIgnoreCase(StatusTicketCons.CANCEL)) {
              dialog1.dismiss();
              dismiss();
            }
          }, error -> {
            dialog1.dismiss();
            ErrorHelper.thrown(error);
          });
    });
    cancelDialogBuilder.setNegativeButton("Tidak", (dialog1, which) -> {
      dialog1.dismiss();
    });
    AlertDialog cancelDialog = cancelDialogBuilder.create();
    cancelDialog.show();
  }

  @OnClick(R.id.btnclose)
  public void onClose() {
    Datum datum = getIntent().getExtras().getParcelable(Datum.class.getSimpleName());
    int id = datum.getTicket().getData().getId();
    DialogFragment dialogFragment = new CloseTicketFragment(String.valueOf(id));
    dialogFragment.show(getFragmentManager(), "TAG");
  }
  public void dismiss() {
    Intent i = new Intent(getApplicationContext(), TicketStatusActivity.class);
    startActivity(i);
    finish();
  }

  private void showContent(Datum datum) {
    Boolean is_true = false;
    Boolean is_true_close = false;
    Log.e("showContent", "TicketStatusDetailActivity" + datum.getTicket().getData().getStatus());
    if (datum.getTicket().getData().getStatus().equals("new")) {
      is_true = true;
    }
    if (datum.getTicket().getData().getStatus().equals("confirmed")) {
      is_true = true;
    }
    if (datum.getTicket().getData().getStatus().equals("done")){
      is_true_close = true;
    }
    if (is_true == true) {
      btncancel.setVisibility(View.VISIBLE);
    }
    if (is_true_close == true){
      btnclose.setVisibility(View.VISIBLE);
    }
    txtTicketNumber.setText(datum.getTicket().getData().getNumber());
    Log.e("showContent", "NotificationDetailActivity" + datum.getTicket().getData().getStaffName());
    txtDate.setText(datum.getTicket().getData().getCreatedAt().getDate());
    txtName.setText(datum.getTicket().getData().getStaffName());
    txtTicketType.setText(datum.getTicket().getData().getTicketType().getData().getName());
    txtPhoneNumber.setText(datum.getTicket().getData().getStaffPhoneNumber());
    txtDescription.setText(datum.getTicket().getData().getDescription());
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.setTitle("Status Tiket");
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onBackPressed() {
    this.startActivity(new Intent(this,NotificationActivity.class));
    finish();
    return;
  }
}
