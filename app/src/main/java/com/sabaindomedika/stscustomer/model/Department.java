package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 01/06/2017.
 */
public class Department implements Parcelable {

  @SerializedName("id") private String id;
  @SerializedName("name") private String name;

  protected Department(Parcel in) {
    id = in.readString();
    name = in.readString();
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

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(name);
  }

  @Override public int describeContents() {
    return 0;
  }

  public static final Creator<Department> CREATOR = new Creator<Department>() {
    @Override public Department createFromParcel(Parcel in) {
      return new Department(in);
    }

    @Override public Department[] newArray(int size) {
      return new Department[size];
    }
  };
}
