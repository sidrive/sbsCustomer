package com.sabaindomedika.stscustomer.apiservice;

import com.sabaindomedika.stscustomer.constant.URLCons;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public interface ApiService {

  @GET("{user_id}")
  Observable<String> getData(@Path("user_id") String userId);
}
