package com.sabaindomedika.stscustomer.utils.helper.network;

import com.sabaindomedika.stscustomer.utils.Preferences;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Fajar Rianda on 08/06/2017.
 */
public class CustomInterceptor implements Interceptor {
  @Override public Response intercept(Chain chain) throws IOException {
    Request request;
    request = chain.request()
        .newBuilder()
        .addHeader("Accept","application/json")
        .addHeader("Authorization", Preferences.getToken() != null
            ? "Bearer ".concat(Preferences.getToken().getAccessToken())
            : "Bearer ")
        .build();

    return chain.proceed(request);
  }
}
