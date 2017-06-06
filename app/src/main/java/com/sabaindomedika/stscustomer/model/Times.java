package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 07/06/2017.
 */
public class Times implements Parcelable {

  @SerializedName("date") private String date;
  @SerializedName("timezone_type") private String timezoneType;
  @SerializedName("timezone") private String timeZone;

  protected Times(Parcel in) {
    date = in.readString();
    timezoneType = in.readString();
    timeZone = in.readString();
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }

  public String getTimezoneType() {
    return timezoneType;
  }

  public void setTimezoneType(String timezoneType) {
    this.timezoneType = timezoneType;
  }

  public static final Creator<Times> CREATOR = new Creator<Times>() {
    @Override public Times createFromParcel(Parcel in) {
      return new Times(in);
    }

    @Override public Times[] newArray(int size) {
      return new Times[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(date);
    dest.writeString(timezoneType);
    dest.writeString(timeZone);
  }
}
