package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 18/05/2017.
 */
public class TicketType implements Parcelable {

  @SerializedName("id") private String id;
  @SerializedName("name") private String name;

  public TicketType() {
  }

  protected TicketType(Parcel in) {
    id = in.readString();
    name = in.readString();
  }

  public static final Creator<TicketType> CREATOR = new Creator<TicketType>() {
    @Override public TicketType createFromParcel(Parcel in) {
      return new TicketType(in);
    }

    @Override public TicketType[] newArray(int size) {
      return new TicketType[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(name);
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
}
