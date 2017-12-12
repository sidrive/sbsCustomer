package com.sabaindomedika.stscustomer.features.ticket.status.detail;

import com.hannesdorfmann.mosby.mvp.MvpView;
import okhttp3.ResponseBody;

/**
 * Created by raka on 12/12/17.
 */

public interface TicketStatusDetailView extends MvpView {
  void showData(ResponseBody body);
  void showLoading(boolean firstLoad);
  void showError(Throwable throwable);
}
