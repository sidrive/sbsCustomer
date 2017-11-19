package com.sabaindomedika.stscustomer.features.notification;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.sabaindomedika.stscustomer.model.Notification;
import com.sabaindomedika.stscustomer.model.Ticket;
import java.util.List;

/**
 * Created by Fajar Rianda on 04/05/2017.
 */
public interface NotificationView extends MvpView {
  void showContent(List<Ticket> notification);
  void showLoading(boolean isFirstLoad,boolean isRefresh);
  void showError(Throwable throwable);
}
