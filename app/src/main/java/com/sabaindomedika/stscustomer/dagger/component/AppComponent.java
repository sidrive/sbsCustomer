package com.sabaindomedika.stscustomer.dagger.component;

import com.sabaindomedika.stscustomer.StsApp;
import com.sabaindomedika.stscustomer.dagger.PerApp;
import com.sabaindomedika.stscustomer.dagger.module.AppModule;
import dagger.Component;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
@PerApp
@Component(modules = { AppModule.class})
public interface AppComponent {
  void inject(StsApp app);
}
