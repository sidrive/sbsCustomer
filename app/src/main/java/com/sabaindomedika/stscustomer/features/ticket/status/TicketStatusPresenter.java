package com.sabaindomedika.stscustomer.features.ticket.status;

import android.content.Context;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.utils.helper.ErrorHelper;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Fajar Rianda on 04/05/2017.
 */
public class TicketStatusPresenter extends MvpNullObjectBasePresenter<TicketStatusView> {

  @Inject ApiService apiService;

  public TicketStatusPresenter(Context context) {
    DaggerInit.networkComponent(context).inject(this);
  }

  public void loadData() {
    apiService.getStatusTicket()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object ->{
          if (object == null){
           return;
          }
          if (object.getData() != null){
            getView().showContent(object.getData());
          }

        }, error -> {
          ErrorHelper.thrown(error);
        });
  }
}
