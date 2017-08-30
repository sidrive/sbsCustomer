package com.sabaindomedika.stscustomer.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sabaindomedika.stscustomer.MainActivity;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.features.notification.NotificationActivity;

/**
 * Created by GeekGarden on 30/08/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

  @Override
  public void onMessageReceived(RemoteMessage remoteMessage) {
    super.onMessageReceived(remoteMessage);
    if (remoteMessage.getData().size()>0){
      Log.e("onMessageReceived", "data" + remoteMessage.getData().toString());
    }
    if (remoteMessage.getNotification()!=null){

      Log.e("onMessageReceived", "body" + remoteMessage.getNotification().getBody());
      String body = remoteMessage.getNotification().getBody();
      String title = remoteMessage.getNotification().getTitle();
      handleNotification(title,body);
    }
  }

  private void handleNotification(String title, String body) {
    Intent intent = new Intent(this, NotificationActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    PendingIntent pendingIntent = PendingIntent.getActivity(this, (int)System.currentTimeMillis() /* Request code */, intent,
        PendingIntent.FLAG_UPDATE_CURRENT);

    Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(title)
        .setContentText(body)
        .setAutoCancel(true)
        .setSound(defaultSoundUri)
        .setContentIntent(pendingIntent);

    NotificationManager notificationManager =
        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

  }
}
