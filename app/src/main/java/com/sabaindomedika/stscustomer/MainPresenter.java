package com.sabaindomedika.stscustomer;

import android.app.AlertDialog;
import android.content.Context;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.features.ticket.status.TicketStatusView;
import com.sabaindomedika.stscustomer.utils.helper.DialogListener;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Raka Settya on 04/05/2017.
 */
public class MainPresenter extends MvpNullObjectBasePresenter<DialogListener> {

  public MainPresenter(MainActivity mainActivity) {

  }

  public AlertDialog showDialog(Context context, DialogListener listener, String[] permission) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle("Request Permission");
    builder.setMessage("Permission Camera, Storage, Phone");
    builder.setPositiveButton("GRANT", (dialogInterface, i) -> {
      listener.dialogPositive(dialogInterface, permission);
    });
    builder.setNegativeButton("CANCEL", (dialogInterface, i) -> {
      listener.dialogNegative(dialogInterface);
    });
    return builder.show();
  }
}
