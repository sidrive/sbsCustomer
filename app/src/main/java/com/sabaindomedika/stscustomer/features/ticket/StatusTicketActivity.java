package com.sabaindomedika.stscustomer.features.ticket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class StatusTicketActivity extends BaseActivity {
  public static void start(Context context) {
    Intent intent = new Intent(context, StatusTicketActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_status_ticket);
    ButterKnife.bind(this);
  }
}
