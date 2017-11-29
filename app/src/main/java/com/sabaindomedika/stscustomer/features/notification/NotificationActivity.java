package com.sabaindomedika.stscustomer.features.notification;

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
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.model.Notification;
import com.sabaindomedika.stscustomer.model.Ticket;
import java.util.List;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class NotificationActivity extends BaseMvpActivity<NotificationView, NotificationPresenter>
    implements NotificationView {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.lvContent) ListView lvContent;
  NotificationAdapter adapter;

  public static void start(Context context) {
    Intent intent = new Intent(context, NotificationActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notification);
    ButterKnife.bind(this);
    DaggerInit.networkComponent(getApplicationContext()).inject(this);
    init();
    setupToolbar();
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.setTitle("Notification");
  }

  private void init() {
    adapter = new NotificationAdapter(this);
    lvContent.setAdapter(adapter);
    presenter.loadDataNotification();
  }

  /* Presenter */
  @NonNull @Override public NotificationPresenter createPresenter() {
    return new NotificationPresenter(getApplicationContext());
  }

  @Override public void showContent(List<Ticket> tickets) {
    adapter.pushData(tickets);
  }

  @Override public void showLoading(boolean isFirstLoad, boolean isRefresh) {

  }

  @Override
  public void showError(Throwable throwable) {

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
