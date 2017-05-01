package com.sabaindomedika.stscustomer.features.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class ProfileActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    ButterKnife.bind(this);
  }

  public static void start(Context context){
    Intent intent = new Intent(context,ProfileActivity.class);
    context.startActivity(intent);
  }
}
