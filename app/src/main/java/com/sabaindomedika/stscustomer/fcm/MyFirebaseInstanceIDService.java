package com.sabaindomedika.stscustomer.fcm;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by GeekGarden on 30/08/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

  @Override
  public void onTokenRefresh() {
    super.onTokenRefresh();
    String fcm_token = FirebaseInstanceId.getInstance().getToken();
    sendToServer(fcm_token);
  }

  private void sendToServer(String fcm_token) {

  }
}
