package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 17/05/2017.
 */
public class Auth extends Responses implements Parcelable {

  public static final Creator<Auth> CREATOR = new Creator<Auth>() {
    @Override public Auth createFromParcel(Parcel in) {
      return new Auth(in);
    }

    @Override public Auth[] newArray(int size) {
      return new Auth[size];
    }
  };
  @SerializedName("username") private String username;
  @SerializedName("password") private String password;
  @SerializedName("grant_type") private String grantType;
  @SerializedName("client_id") private String clientId;
  @SerializedName("client_secret") private String clientSecret;
  @SerializedName("scope") private String scope;

  public Auth() {

  }

  protected Auth(Parcel in) {
    username = in.readString();
    password = in.readString();
    grantType = in.readString();
    clientId = in.readString();
    clientSecret = in.readString();
    scope = in.readString();
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public String getGrantType() {
    return grantType;
  }

  public void setGrantType(String grantType) {
    this.grantType = grantType;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(username);
    dest.writeString(password);
    dest.writeString(grantType);
    dest.writeString(clientId);
    dest.writeString(clientSecret);
    dest.writeString(scope);
  }

  @Override public int describeContents() {
    return 0;
  }
}
