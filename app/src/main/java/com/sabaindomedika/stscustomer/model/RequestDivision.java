package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 05/06/2017.
 */
public class RequestDivision implements Parcelable {

  @SerializedName("id") private String id;
  @SerializedName("name") private String name;

  protected RequestDivision(Parcel in) {
    id = in.readString();
    name = in.readString();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(name);
  }

  @Override public int describeContents() {
    return 0;
  }

  public static final Creator<RequestDivision> CREATOR = new Creator<RequestDivision>() {
    @Override public RequestDivision createFromParcel(Parcel in) {
      return new RequestDivision(in);
    }

    @Override public RequestDivision[] newArray(int size) {
      return new RequestDivision[size];
    }
  };

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
