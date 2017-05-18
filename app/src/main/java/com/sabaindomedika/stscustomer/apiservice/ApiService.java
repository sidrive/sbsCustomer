package com.sabaindomedika.stscustomer.apiservice;

import com.sabaindomedika.stscustomer.constant.URLCons;
import com.sabaindomedika.stscustomer.model.Auth;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.model.Responses;
import com.sabaindomedika.stscustomer.model.Token;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public interface ApiService {

  @GET("{user_id}")
  Observable<String> getData(@Path("user_id") String userId);

  @POST(URLCons.URL_LOGIN)
  Observable<Token> login(@Body Auth auth);

  @POST(URLCons.URL_OPEN_TICKET)
  Observable<Responses<Ticket>> postOpenTicket(@Body Ticket ticket);

  @GET(URLCons.URL_STATUS_TICKET)
  Observable<Responses<List<Ticket>>> getStatusTicket();
}
