package com.sabaindomedika.stscustomer.features.ticket.status.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import com.sabaindomedika.stscustomer.features.ticket.status.TicketStatusHistoryFragment;
import com.sabaindomedika.stscustomer.features.ticket.status.TicketStatusOpenFragment;

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
        return new TicketStatusOpenFragment();
      default:
        return new TicketStatusHistoryFragment();
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
