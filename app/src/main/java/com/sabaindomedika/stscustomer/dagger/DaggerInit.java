package com.sabaindomedika.stscustomer.dagger;

import android.app.Application;
import android.content.Context;
import com.sabaindomedika.stscustomer.StsApp;
import com.sabaindomedika.stscustomer.dagger.component.AppComponent;
import com.sabaindomedika.stscustomer.dagger.component.DaggerAppComponent;
import com.sabaindomedika.stscustomer.dagger.component.DaggerNetworkComponent;
import com.sabaindomedika.stscustomer.dagger.component.NetworkComponent;
import com.sabaindomedika.stscustomer.dagger.module.AppModule;
import com.sabaindomedika.stscustomer.dagger.module.NetworkModule;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class DaggerInit {

  public static AppComponent appComponent(Application application) {
    return DaggerAppComponent.builder().appModule(new AppModule(application)).build();
  }

  public static NetworkComponent networkComponent(Context context) {
    return DaggerNetworkComponent.builder()
        .appComponent(StsApp.component())
        .networkModule(new NetworkModule(context))
        .build();
  }
}
