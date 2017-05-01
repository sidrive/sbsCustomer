package com.sabaindomedika.stscustomer.basecommon;

import android.app.FragmentManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class BaseActivity extends AppCompatActivity {

  public FragmentManager getBaseFragmentManager() {
    return getFragmentManager();
  }
}
