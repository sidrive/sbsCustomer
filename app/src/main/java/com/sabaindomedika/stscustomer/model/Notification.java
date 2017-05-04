package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 04/05/2017.
 */
public class Notification implements Parcelable{

  @SerializedName("content") private String content;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Notification() {
  }

  protected Notification(Parcel in) {
    content = in.readString();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(content);
  }

  @Override public int describeContents() {
    return 0;
  }

  public static final Creator<Notification> CREATOR = new Creator<Notification>() {
    @Override public Notification createFromParcel(Parcel in) {
      return new Notification(in);
    }

    @Override public Notification[] newArray(int size) {
      return new Notification[size];
    }
  };
}
