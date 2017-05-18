package com.sabaindomedika.stscustomer.features.ticket.status;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseMvpActivity;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.model.TicketOld;
import java.util.List;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class TicketStatusActivity extends BaseMvpActivity<TicketStatusView, TicketStatusPresenter>
    implements TicketStatusView {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.lvContent) ListView lvContent;
  TicketStatusAdapater adapter;

  public static void start(Context context) {
    Intent intent = new Intent(context, TicketStatusActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ticket_status);
    ButterKnife.bind(this);
    init();
    setupToolbar();
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.setTitle("Status Tiket");
  }

  private void init() {
    adapter = new TicketStatusAdapater(this,getBaseFragmentManager());
    lvContent.setAdapter(adapter);
    presenter.loadData();
  }

  /* Presenter */
  @NonNull @Override public TicketStatusPresenter createPresenter() {
    return new TicketStatusPresenter(this);
  }

  @Override public void showContent(List<Ticket> tickets) {
      adapter.pushData(tickets);
  }

  /* Menu */
  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
