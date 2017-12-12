package com.sabaindomedika.stscustomer.features.ticket.status;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

  public void loadDataOpen() {
    getView().showLoading(true, true);
    apiService.getTicketStatusOpen()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object -> {
          if (object == null) {
            return;
          }
          getView().showLoading(false, true);
          getView().showContent(object.getData());
        }, error -> {
          getView().showLoading(false, true);
          getView().showError(error);
        });
  }

  public void loadDataHistory() {
    getView().showLoading(true, true);
    apiService.getTicketStatusHistory("1")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object -> {
          if (object == null) {
            return;
          }
          getView().showLoading(false, true);
          getView().showContent(object.getData());
        }, error -> {
          getView().showLoading(false, true);
          getView().showError(error);
        });
  }
}
