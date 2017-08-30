package com.sabaindomedika.stscustomer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by GeekGarden on 30/08/2017.
 */

public class FcmToken {
  @SerializedName("fcm_token")
  @Expose
  private String fcmToken;
  public String getFcmToken() {
    return fcmToken;
  }

  public void setFcmToken(String fcmToken) {
    this.fcmToken = fcmToken;
  }
}
