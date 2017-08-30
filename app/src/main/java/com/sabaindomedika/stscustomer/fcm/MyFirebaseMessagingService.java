package com.sabaindomedika.stscustomer.fcm;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by GeekGarden on 30/08/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

  @Override
  public void onMessageReceived(RemoteMessage remoteMessage) {
    super.onMessageReceived(remoteMessage);
    if (remoteMessage.getData().size()>0){
      Log.e("onMessageReceived", "MyFirebaseMessagingService" + remoteMessage.getData().toString());
    }
    if (remoteMessage.getNotification()!=null){
      Log.e("onMessageReceived", "MyFirebaseMessagingService" + remoteMessage.getNotification().toString());
    }
  }
}
