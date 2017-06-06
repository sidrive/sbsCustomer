package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 17/05/2017.
 */
public class Responses<T> implements Parcelable{

  @SerializedName("error") private String error;
  @SerializedName("message") private String message;
  @SerializedName("hint") private String hint;
  @SerializedName("data") private T data;

  protected Responses(Parcel in) {
    error = in.readString();
    message = in.readString();
    hint = in.readString();
  }

  public static final Creator<Responses> CREATOR = new Creator<Responses>() {
    @Override public Responses createFromParcel(Parcel in) {
      return new Responses(in);
    }

    @Override public Responses[] newArray(int size) {
      return new Responses[size];
    }
  };

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getHint() {
    return hint;
  }

  public void setHint(String hint) {
    this.hint = hint;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(error);
    dest.writeString(message);
    dest.writeString(hint);
  }
}
