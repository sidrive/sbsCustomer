package com.sabaindomedika.stscustomer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sabaindomedika.stscustomer.features.notification.NotificationActivity;
import com.sabaindomedika.stscustomer.features.profile.ProfileActivity;
import com.sabaindomedika.stscustomer.features.ticket.OpenTicketActivity;
import com.sabaindomedika.stscustomer.features.ticket.StatusTicketActivity;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }


  @OnClick(R.id.txtOpenTicket) public void onOpenTicket(){
    OpenTicketActivity.start(this);
  }

  @OnClick(R.id.txtNotification) public void onNotification(){
    NotificationActivity.start(this);
  }

  @OnClick(R.id.txtStatusTicket) public void OnStatusTicket(){
    StatusTicketActivity.start(this);
  }

  @OnClick(R.id.txtProfile) public void onProfile(){
    ProfileActivity.start(this);
  }
}
