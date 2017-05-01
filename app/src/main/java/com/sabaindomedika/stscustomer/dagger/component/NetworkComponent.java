package com.sabaindomedika.stscustomer.dagger.component;

import com.sabaindomedika.stscustomer.dagger.PerActivity;
import com.sabaindomedika.stscustomer.dagger.module.NetworkModule;
import dagger.Component;

/**
 * Created by Fajar Rianda on 26/01/2017.
 */
@PerActivity @Component(modules = { NetworkModule.class }, dependencies = { AppComponent.class })
public interface NetworkComponent {

}
