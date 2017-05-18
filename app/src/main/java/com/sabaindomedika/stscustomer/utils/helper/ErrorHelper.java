package com.sabaindomedika.stscustomer.utils.helper;

import android.util.Log;
import com.google.gson.Gson;
import com.sabaindomedika.stscustomer.model.Responses;
import com.sabaindomedika.stscustomer.utils.Logger;
import com.sabaindomedika.stscustomer.utils.Toasts;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Fajar Rianda on 14/03/2017.
 */
public class ErrorHelper {

  // We had non-200 http error
  public static boolean httpException(Throwable throwable) {
    return throwable instanceof HttpException;
  }

  public static boolean unknownHostException(Throwable throwable) {
    return throwable instanceof UnknownHostException;
  }

  public static void thrown(Throwable throwable) {
    if (unknownHostException(throwable)) {
      Toasts.show("Cek koneksi anda");
    } else if (httpException(throwable)) {
      Toasts.show("Silahkan Coba Lagi");
    }
  }

  public static String getError(Throwable throwable) {
    try {
      Charset charset = Charset.forName("UTF-8");
      ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
      BufferedSource source = responseBody.source();
      source.request(Long.MAX_VALUE);
      Buffer buffer = source.buffer();
      Gson gson = new Gson();
      Responses response = gson.fromJson(buffer.clone().readString(charset), Responses.class);
      return response.getError();
    } catch (Exception e) {
      Logger.log(Log.DEBUG, "error :" + e.getMessage());
    }
    return "Silahkan Coba Lagi";
  }
}
