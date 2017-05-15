package com.sabaindomedika.stscustomer.utils;

import android.content.Context;

/**
 * Created by Fajar Rianda on 08/05/2017.
 */
public class ContextProvider {

  private static ContextProvider instance;
  private Context context;

  private static ContextProvider getInstance(){
    if (instance == null){
      instance = new ContextProvider();
    }
    return instance;
  }

  private void init(Context context){
    this.context = context;
  }
  private Context getContext(){
    if (this.context == null){
      throw new IllegalStateException("Must call init first before getContext()");
    } else {
      return this.context;
    }
  }

  public static void install(Context context){
    getInstance().init(context);
  }
  public static Context get(){
    return getInstance().getContext();
  }
}
