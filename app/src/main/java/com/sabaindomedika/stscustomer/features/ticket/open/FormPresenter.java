package com.sabaindomedika.stscustomer.features.ticket.open;

import android.app.ProgressDialog;
import android.content.Context;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.model.Instrument;
import com.sabaindomedika.stscustomer.model.RequestDivision;
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

  public void loadData(String ticketTypeId, String divisionId) {
    getView().showLoading(true);
    if (ticketTypeId.equals("1")) {
      if (divisionId.equals("3")) {
        getView().showDeviceName();
        getView().showLoading(false);
      } else {
        loadInstrument(divisionId.equals("4")
            ? "2"
            : "1");
      }
      return;
    }

    if (ticketTypeId.equals("2")) {
      loadRequestDivisions(divisionId);
      return;
    }

    if (ticketTypeId.equals("3")) {
      loadDepartment();
    }
  }

  private void loadInstrument(String categoryId) {
    if (Preferences.getHashMapInstrument() != null && Preferences.getHashMapInstrument()
        .containsKey(Instrument.class.getSimpleName().concat(categoryId))) {
      getView().showInstrument(
          Preferences.getInstrument(Instrument.class.getSimpleName().concat(categoryId)));
      getView().showLoading(false);
    } else {
      apiService.getInstrument(categoryId)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(object -> {
            getView().showInstrument(object.getData());
            Preferences.setInstrument(Instrument.class.getSimpleName().concat(categoryId),
                object.getData());

            getView().showLoading(false);
          }, error -> {
            getView().showLoading(false);
            getView().showError(error);
          });
    }
  }

  private void loadRequestDivisions(String divisionId) {
    if (Preferences.getHashMapRequest() != null && Preferences.getHashMapRequest()
        .containsKey(RequestDivision.class.getSimpleName().concat(divisionId))) {
      getView().showRequestDivision(
          Preferences.getRequestDivision(RequestDivision.class.getSimpleName().concat(divisionId)));
      getView().showLoading(false);
    } else {
      apiService.getRequestDivisions(divisionId)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(object -> {
            getView().showRequestDivision(object.getData());
            Preferences.setRequestDvision(RequestDivision.class.getSimpleName().concat(divisionId),
                object.getData());
            getView().showLoading(false);
          }, error -> {
            getView().showLoading(false);
            getView().showError(error);
          });
    }
  }

  private void loadDepartment() {

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
