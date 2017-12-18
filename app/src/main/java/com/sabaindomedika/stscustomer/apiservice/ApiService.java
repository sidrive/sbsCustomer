package com.sabaindomedika.stscustomer.apiservice;

import com.sabaindomedika.stscustomer.constant.URLCons;
import com.sabaindomedika.stscustomer.model.Auth;
import com.sabaindomedika.stscustomer.model.BodyClose;
import com.sabaindomedika.stscustomer.model.Category;
import com.sabaindomedika.stscustomer.model.Department;
import com.sabaindomedika.stscustomer.model.Division;
import com.sabaindomedika.stscustomer.model.FcmToken;
import com.sabaindomedika.stscustomer.model.Instrument;
import com.sabaindomedika.stscustomer.model.part.ResponsePart;
import com.sabaindomedika.stscustomer.model.profile.ResponseProfile;
import com.sabaindomedika.stscustomer.model.RequestDivision;
import com.sabaindomedika.stscustomer.model.Responses;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.model.User;
import com.sabaindomedika.stscustomer.model.auth.ResponseLogin;
import com.sabaindomedika.stscustomer.model.notification.ResponseNotification;
import com.sabaindomedika.stscustomer.model.servicereport.ResponseServiceReport;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public interface ApiService {

  @GET("{user_id}")
  Observable<String> getData(@Path("user_id") String userId);

  @POST(URLCons.URL_LOGIN)
  Observable<ResponseLogin> login(@Body Auth auth);

  @Headers("Content-Type: application/json")
  @PUT(URLCons.URL_FCM)
  Observable<Responses<User>> updateFcm(@Body FcmToken fcm_token);

  @GET(URLCons.URL_PROFILE)
  Observable<ResponseProfile> getUserProfile();

  @GET(URLCons.URL_DIVISIONS)
  Observable<Responses<List<Division>>> getDivisions();

  @GET(URLCons.URL_REQUEST_DIVISIONS)
  Observable<Responses<List<RequestDivision>>> getRequestDivisions(@Path("division_id") String divisionID);

  @GET(URLCons.URL_INSTRUMENT)
  Observable<Responses<List<Instrument>>> getInstrument(@Query("instrument_category_id") String instrument_category_id);

  @GET(URLCons.URL_DEPARTMENTS)
  Observable<Responses<List<Department>>> getDepartments();

  @POST(URLCons.URL_OPEN_TICKET)
  Observable<Responses<Ticket>> postOpenTicket(@Body Ticket ticket);

  @PUT(URLCons.URL_CLOSE_TICKET)
  Observable<Responses<Ticket>> closeTicket(@Path("ticket") String ticketId, @Body BodyClose bodyClose);

  @PUT(URLCons.URL_CANCEL_TICKET)
  Observable<Responses<Ticket>> cancelTicket(@Path("ticket") String ticketId);

  @GET(URLCons.URL_TICKET_STATUS)
  Observable<Responses<List<Ticket>>> getTicketStatusOpen();

  @GET(URLCons.URL_TICKET_STATUS)
  Observable<Responses<List<Ticket>>> getTicketStatusHistory(@Query("is_closed") String isClosed);

  @GET(URLCons.URL_TICKET_NOTIFICATION)
  Observable<ResponseNotification> getTicketNotification();

  @PUT(URLCons.URL_TICKET_READ)
  Observable<ResponseNotification> isread(@Path("notification_id") String notification_id);

  @GET(URLCons.URL_INTERFACE)
  Observable<Responses<List<Category>>> getInterface();

  @Streaming
  @Headers({"Content-Type: application/json"})
  @GET(URLCons.URL_DOWNLOAD_PDF)
  Call<ResponseBody> downloadPdf(@Path("id")String id);

  @GET(URLCons.URL_SERVICE_REPORT)
  Observable<ResponseServiceReport> serviceReport(@Path("ticket") String TicketId);

  @GET(URLCons.URL_SERVICE_PART)
  Observable<ResponsePart> servicePart(@Path("ticket") String TicketId, @Path("service_report") Integer service_report);


}
