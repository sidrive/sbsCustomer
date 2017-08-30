package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 21/05/2017.
 */
public class User implements Parcelable{

  @SerializedName("id") private String id;
  @SerializedName("email") private String email;
  @SerializedName("name") private String name;
  @SerializedName("phone_number") private String phone;
  @SerializedName("type") private String client;
  @SerializedName("fcm_token") private String fcm_token;

  protected User(Parcel in) {
    id = in.readString();
    email = in.readString();
    name = in.readString();
    phone = in.readString();
    client = in.readString();
    fcm_token = in.readString();
  }

  public static final Creator<User> CREATOR = new Creator<User>() {
    @Override public User createFromParcel(Parcel in) {
      return new User(in);
    }

    @Override public User[] newArray(int size) {
      return new User[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(email);
    dest.writeString(name);
    dest.writeString(phone);
    dest.writeString(client);
    dest.writeString(fcm_token);
  }

  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFcm_token() {
    return fcm_token;
  }

  public void setFcm_token(String fcm_token) {
    this.fcm_token = fcm_token;
  }
}
