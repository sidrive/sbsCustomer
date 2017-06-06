package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 06/06/2017.
 */
public class Content<T> implements Parcelable {

  @SerializedName("data") private T data;

  protected Content(Parcel in) {
  }

  public static final Creator<Content> CREATOR = new Creator<Content>() {
    @Override public Content createFromParcel(Parcel in) {
      return new Content(in);
    }

    @Override public Content[] newArray(int size) {
      return new Content[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
