package com.sabaindomedika.stscustomer.model;

import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 17/05/2017.
 */
public class Responses<T> {

  @SerializedName("error") private String error;
  @SerializedName("message") private String message;
  @SerializedName("hint") private String hint;
  @SerializedName("data") private T Data;

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
    return Data;
  }

  public void setData(T data) {
    Data = data;
  }
}
