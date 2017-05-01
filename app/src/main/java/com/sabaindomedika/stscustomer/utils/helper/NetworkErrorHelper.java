package com.sabaindomedika.stscustomer.utils.helper;

import java.net.UnknownHostException;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Fajar Rianda on 14/03/2017.
 */
public class NetworkErrorHelper {

  // We had non-200 http error
  public static boolean httpException(Throwable throwable){
    return throwable instanceof HttpException;
  }
  public static boolean UnknownHostException(Throwable throwable){
    return throwable instanceof UnknownHostException;
  }
}
