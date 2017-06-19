package com.sabaindomedika.stscustomer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;
import com.sabaindomedika.stscustomer.features.notification.NotificationActivity;
import com.sabaindomedika.stscustomer.features.profile.ProfileActivity;
import com.sabaindomedika.stscustomer.features.ticket.open.OpenTicketActivity;
import com.sabaindomedika.stscustomer.features.ticket.status.TicketStatusActivity;

public class MainActivity extends BaseActivity {

  public static void start(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.txtOpenTicket) public void onOpenTicket() {
    OpenTicketActivity.start(this);
  }

  @OnClick(R.id.txtNotification) public void onNotification() {
    NotificationActivity.start(this);
  }

  @OnClick(R.id.txtStatusTicket) public void OnStatusTicket() {
    TicketStatusActivity.start(this);
  }

  @OnClick(R.id.txtProfile) public void onProfile() {
    ProfileActivity.start(this);
  }
}
