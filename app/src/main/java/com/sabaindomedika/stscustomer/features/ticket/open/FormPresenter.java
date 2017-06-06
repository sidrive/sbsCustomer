package com.sabaindomedika.stscustomer.features.ticket.open;

import android.app.ProgressDialog;
import android.content.Context;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.utils.Preferences;
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

  public void loadData(String ticketTypeId,String divisionId){
    switch (ticketTypeId){
      case "2":
        loadRequestDivisions(divisionId);
        break;
      case "3":
        loadDepartment();
        break;
      default:
        getView().showLoading(false);
        break;
    }
  }
  private void loadRequestDivisions(String divisionId) {
    getView().showLoading(true);
    apiService.getRequestDivisions(divisionId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object -> {
          getView().showRequestDivision(object.getData());
          getView().showLoading(false);
        }, error -> {
          getView().showLoading(false);
          getView().showError(error);
        });
  }

  private void loadDepartment() {
    getView().showLoading(true);
    if (Preferences.getDepartment() == null) {
      apiService.getDepartments()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(object -> {
            Preferences.setDepartment(object.getData());
            getView().showDepartment(object.getData());
            getView().showLoading(false);
          }, error -> {
            getView().showLoading(false);
            getView().showError(error);
          });
    } else {
      getView().showDepartment(Preferences.getDepartment());
      getView().showLoading(false);
    }
  }
}
