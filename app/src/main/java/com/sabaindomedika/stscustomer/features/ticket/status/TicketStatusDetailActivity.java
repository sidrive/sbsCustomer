package com.sabaindomedika.stscustomer.features.ticket.status;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.model.TicketType;
import com.sabaindomedika.stscustomer.utils.Strings;

/**
 * Created by Fajar Rianda on 18/06/2017.
 */
public class TicketStatusDetailActivity extends BaseActivity {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.txtTicketNumber) TextView txtTicketNumber;
  @Bind(R.id.txtDate) TextView txtDate;
  @Bind(R.id.txtTicketType) TextView txtTicketType;
  @Bind(R.id.txtName) TextView txtName;
  @Bind(R.id.txtPhoneNumber) TextView txtPhoneNumber;
  @Bind(R.id.txtDescription) TextView txtDescription;

  public static void start(Context context, Ticket ticket) {
    Bundle bundle = new Bundle();
    bundle.putParcelable(Ticket.class.getSimpleName(), ticket);
    bundle.putParcelable(TicketType.class.getSimpleName(), ticket.getTicketType().getData());
    Intent intent = new Intent(context, TicketStatusDetailActivity.class);
    intent.putExtras(bundle);
    context.startActivity(intent);
  }

  public static void start(Context context) {
    Intent intent = new Intent(context, TicketStatusDetailActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ticket_status_detail);
    ButterKnife.bind(this);
    init();
    setupToolbar();
  }

  private void init() {
    Ticket ticket = getIntent().getExtras().getParcelable(Ticket.class.getSimpleName());
    TicketType ticketType = getIntent().getExtras().getParcelable(TicketType.class.getSimpleName());
    txtTicketType.setText(ticketType.getName());
    showContent(ticket);
  }

  private void showContent(Ticket ticket) {
    txtTicketNumber.setText(ticket.getNumber());
    txtDate.setText(Strings.getDate(ticket.getTimes().getDate()));
    txtName.setText(ticket.getStaffName());
    txtPhoneNumber.setText(ticket.getStaffPhoneNumber());
    txtDescription.setText(ticket.getDescription());
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.setTitle("Status Tiket");
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
