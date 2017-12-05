package com.sabaindomedika.stscustomer.features.notification;

import android.content.Context;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.model.Notification;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Fajar Rianda on 04/05/2017.
 */
public class NotificationPresenter extends MvpNullObjectBasePresenter<NotificationView> {
  @Inject
  ApiService apiService;
  public NotificationPresenter(Context context)  {
    DaggerInit.networkComponent(context).inject(this);
  }

  public void loadDataNotification() {
    getView().showLoading(true, true);
    apiService.getTicketNotification()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object -> {
              if (object == null) {
                return;
              }
              getView().showLoading(false, true);
              getView().showContent(object.getData());
            }
        ,throwable -> {
              getView().showLoading(false, true);
              getView().showError(throwable);
            });
  }
}
