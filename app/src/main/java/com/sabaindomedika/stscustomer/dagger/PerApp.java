package com.sabaindomedika.stscustomer.dagger;

import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
@Scope
@Retention(RUNTIME)
public @interface PerApp {
}
