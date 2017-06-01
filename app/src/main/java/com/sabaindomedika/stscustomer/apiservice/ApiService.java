package com.sabaindomedika.stscustomer.apiservice;

import com.sabaindomedika.stscustomer.constant.URLCons;
import com.sabaindomedika.stscustomer.model.Auth;
import com.sabaindomedika.stscustomer.model.Department;
import com.sabaindomedika.stscustomer.model.Division;
import com.sabaindomedika.stscustomer.model.Responses;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.model.Token;
import com.sabaindomedika.stscustomer.model.User;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

  @GET(URLCons.URL_PROFILE)
  Observable<Responses<User>> getUserProfile();

  @GET(URLCons.URL_DIVISIONS)
  Observable<Responses<List<Division>>> getDivisions();

  @GET(URLCons.URL_REQUEST_DIVISIONS)
  Observable<Responses<List<Division>>> getRequestDivisions(@Path("division_id") String divisionID);

  @GET(URLCons.URL_DEPARTMENTS)
  Observable<Responses<List<Department>>> getDepartments();

  @POST(URLCons.URL_OPEN_TICKET)
  Observable<Responses<Ticket>> postOpenTicket(@Body Ticket ticket);

  @PUT(URLCons.URL_CLOSE_TICKET)
  Observable<Responses<Ticket>> closeTicket();

  @GET(URLCons.URL_STATUS_TICKET)
  Observable<Responses<List<Ticket>>> getStatusTicket();
}
