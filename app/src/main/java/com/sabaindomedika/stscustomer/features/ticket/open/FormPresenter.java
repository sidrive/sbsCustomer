package com.sabaindomedika.stscustomer.features.ticket.open;

import android.app.ProgressDialog;
import android.content.Context;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.utils.helper.ErrorHelper;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Fajar Rianda on 17/05/2017.
 */
public class FormPresenter extends MvpNullObjectBasePresenter<FormView> {

  Context context;
  @Inject ApiService apiService;

  public FormPresenter(Context context) {
    this.context = context;
    DaggerInit.networkComponent(context).inject(this);
  }

  public void postOpenTicket(Ticket ticket) {
    ProgressDialog dialog = new ProgressDialog(context);
    dialog.setMessage("Loading...");
    dialog.show();
    apiService.postOpenTicket(ticket)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object -> {
          dialog.dismiss();
          if (object == null) {
            getView().statusOpenTicket(false);
            return;
          }

          if (object.getData() != null) {
            getView().statusOpenTicket(true);
          }
        }, error -> {
          dialog.dismiss();
          ErrorHelper.thrown(error);
        });
  }

  ;
}
