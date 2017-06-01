package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.sabaindomedika.stscustomer.R;

/**
 * Created by Fajar Rianda on 21/05/2017.
 */
public class User implements Parcelable{

  @SerializedName("id") private String id;
  @SerializedName("email") private String email;
  @SerializedName("name") private String name;
  @SerializedName("phone_number") private String phone;
  @SerializedName("type") private String client;

  protected User(Parcel in) {
    id = in.readString();
    email = in.readString();
    name = in.readString();
    phone = in.readString();
    client = in.readString();
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
  }
}
