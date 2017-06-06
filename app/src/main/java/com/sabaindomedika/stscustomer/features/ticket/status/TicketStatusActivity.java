package com.sabaindomedika.stscustomer.features.ticket.status;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;
import com.sabaindomedika.stscustomer.features.ticket.status.adapter.TicketStatusPagerAdapter;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class TicketStatusActivity extends BaseActivity {

  TicketStatusPagerAdapter adapter;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.tabLayout) TabLayout tabLayout;
  @Bind(R.id.pager) ViewPager viewPager;

  public static void start(Context context) {
    Intent intent = new Intent(context, TicketStatusActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ticket_status);
    ButterKnife.bind(this);
    setupToolbar();
    init();
  }

  private void init() {
    adapter = new TicketStatusPagerAdapter(getBaseFragmentManager());
    viewPager.setAdapter(adapter);
    viewPager.setOffscreenPageLimit(2);
    tabLayout.setupWithViewPager(viewPager);
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.setTitle("Status Tiket");
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
