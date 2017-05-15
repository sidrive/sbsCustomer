package com.sabaindomedika.stscustomer.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 08/05/2017.
 */
public class Token {

  @SerializedName("token_type") private String tokenType;
  @SerializedName("access_token") private String accessToken;
  @SerializedName("refresh_token") private String refreshToken;
  @SerializedName("grant_type") private String grantType;

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getGrantType() {
    return grantType;
  }

  public void setGrantType(String grantType) {
    this.grantType = grantType;
  }
}
