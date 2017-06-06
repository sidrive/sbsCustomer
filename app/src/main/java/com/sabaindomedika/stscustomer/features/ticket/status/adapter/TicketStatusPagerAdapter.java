package com.sabaindomedika.stscustomer.features.ticket.status.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import com.sabaindomedika.stscustomer.features.ticket.status.HistoryTicketStatusFragment;
import com.sabaindomedika.stscustomer.features.ticket.status.OpenTicketStatusFragment;

/**
 * Created by Fajar Rianda on 06/06/2017.
 */
public class TicketStatusPagerAdapter extends FragmentPagerAdapter {

  public TicketStatusPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new OpenTicketStatusFragment();
      default:
        return new HistoryTicketStatusFragment();
    }
  }

  @Override public int getCount() {
    return 2;
  }

  @Override public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return "Open";
      default:
        return "History";
    }
  }
}
