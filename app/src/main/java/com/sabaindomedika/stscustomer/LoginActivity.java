package com.sabaindomedika.stscustomer;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;

/**
 * Created by Fajar Rianda on 15/05/2017.
 */
public class LoginActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
  }
}
