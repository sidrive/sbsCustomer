package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 18/05/2017.
 */
public class InstrumentCategory implements Parcelable {

  @SerializedName("id") private String id;
  @SerializedName("name") private String name;

  public InstrumentCategory() {
  }

  protected InstrumentCategory(Parcel in) {
    id = in.readString();
    name = in.readString();
  }

  public static final Creator<InstrumentCategory> CREATOR = new Creator<InstrumentCategory>() {
    @Override public InstrumentCategory createFromParcel(Parcel in) {
      return new InstrumentCategory(in);
    }

    @Override public InstrumentCategory[] newArray(int size) {
      return new InstrumentCategory[size];
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
