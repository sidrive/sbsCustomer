package com.sabaindomedika.stscustomer.features.ticket.status.detail;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.utils.Toasts;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by raka on 12/12/17.
 */

public class TicketStatusDetailPresenter extends MvpNullObjectBasePresenter<TicketStatusDetailView> {

  @Inject ApiService apiService;

  public TicketStatusDetailPresenter(Context context) {
    DaggerInit.networkComponent(context).inject(this);
  }

  public void downloadPdf(String idtiket,Context context) {
    Call<ResponseBody> call = apiService.downloadPdf(idtiket);

    call.enqueue(new Callback<ResponseBody>() {
      @Override
      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()) {
          Log.d(TAG, "server contacted and has file");
          getView().showData(response.body());
          getView().showLoading(false);
          Toasts.show(context, "PDF Success Download wait to open");
          Log.e("onResponse", "DetailEnded" + response.body().byteStream());
        } else {
          getView().showLoading(false);
          Log.d(TAG, "server contact failed");
        }
  }
      @Override
      public void onFailure(Call<ResponseBody> call, Throwable throwable) {
        getView().showLoading(false);
        Log.d(TAG, "server contact failed");
      }
    });
  }
}
