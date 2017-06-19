package com.sabaindomedika.stscustomer;

import android.support.multidex.MultiDexApplication;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.dagger.component.AppComponent;
import com.sabaindomedika.stscustomer.utils.ContextProvider;
import com.sabaindomedika.stscustomer.utils.Logger;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class StsApp extends MultiDexApplication {

  private static AppComponent appComponent;

  @Override public void onCreate() {
    super.onCreate();
    setupContextProvider();
    setupComponent();
    setupFirebase();
  }

  private void setupFirebase() {
    FirebaseAnalytics.getInstance(this);
    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread t, Throwable e) {
        Logger.log(Log.DEBUG,"error message" + e.getMessage());
        FirebaseCrash.report(e);
      }
    });
  }

  private void setupContextProvider() {
    ContextProvider.install(this);
  }

  private void setupComponent() {
    appComponent = DaggerInit.appComponent(this);
    appComponent.inject(this);
  }

  public static AppComponent component() {
    return appComponent;
  }

}
