package com.sabaindomedika.stscustomer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.firebase.iid.FirebaseInstanceId;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.features.notification.NotificationActivity;
import com.sabaindomedika.stscustomer.features.profile.ProfileActivity;
import com.sabaindomedika.stscustomer.features.ticket.open.OpenTicketActivity;
import com.sabaindomedika.stscustomer.features.ticket.status.TicketStatusActivity;
import com.sabaindomedika.stscustomer.model.FcmToken;
import com.sabaindomedika.stscustomer.utils.Preferences;

import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
  @Inject
  ApiService apiService;
  public static void start(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    context.startActivity(intent);

  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    DaggerInit.networkComponent(this).inject(this);
    String fcm_token = FirebaseInstanceId.getInstance().getToken();
    updateFCMToken(fcm_token);

  }

  private void updateFCMToken(String fcm_token) {
    Log.e("updateFCMToken", "MainActivity" + fcm_token);
    FcmToken fcm = new FcmToken();
    fcm.setFcmToken(fcm_token);
    apiService.updateFcm(fcm)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(userResponses -> {},throwable -> {});
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

  @OnClick(R.id.btnLogout) public void onLogout() {
    Preferences.clear();
    LoginActivity.start(this);
  }
}
