package com.sabaindomedika.stscustomer;

import android.app.Application;
import android.support.multidex.MultiDexApplication;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.dagger.component.AppComponent;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class StsApp extends MultiDexApplication {

  private static AppComponent appComponent;

  @Override public void onCreate() {
    super.onCreate();
    setupComponent();
  }
  private void setupComponent() {
    appComponent = DaggerInit.appComponent(this);
    appComponent.inject(this);
  }

  public static AppComponent component() {
    return appComponent;
  }

}
