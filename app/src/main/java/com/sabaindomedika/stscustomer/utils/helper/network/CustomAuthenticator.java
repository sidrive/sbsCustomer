package com.sabaindomedika.stscustomer.utils.helper.network;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.sabaindomedika.stscustomer.constant.ErrorCons;
import com.sabaindomedika.stscustomer.model.Responses;
import com.sabaindomedika.stscustomer.utils.Logger;
import com.sabaindomedika.stscustomer.utils.Preferences;
import com.sabaindomedika.stscustomer.utils.Toasts;
import java.io.IOException;
import java.nio.charset.Charset;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by Fajar Rianda on 17/05/2017.
 */
public class CustomAuthenticator implements Authenticator {

  Context context;

  public CustomAuthenticator(Context context) {
    this.context = context;
  }

  @Override public Request authenticate(Route route, Response response) throws IOException {

    String error = getError(response);
    if (error.equals(ErrorCons.INVALID_CREDENTIAL)){
      return null;
    }

    return response.request()
        .newBuilder()
        .removeHeader("Authorization")
        .addHeader("Authorization", Preferences.getToken() != null
            ? "Bearer ".concat(Preferences.getToken().getAccessToken())
            : "Bearer ")
        .build();
  }

  private String getError(Response responses) {
    try {
      Charset charset = Charset.forName("UTF-8");
      ResponseBody responseBody = responses.body();
      BufferedSource source = responseBody.source();
      source.request(Long.MAX_VALUE);
      Buffer buffer = source.buffer();
      Gson gson = new Gson();
      Responses response =
          gson.fromJson(buffer.clone().readString(charset), Responses.class);
      return response.getError();
    } catch (Exception e) {
      Logger.log(Log.DEBUG, "error :" + e.getMessage());
    }
    return "";
  }
}
