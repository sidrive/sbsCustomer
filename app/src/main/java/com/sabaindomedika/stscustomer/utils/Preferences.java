package com.sabaindomedika.stscustomer.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.sabaindomedika.stscustomer.model.Token;

/**
 * Created by Fajar Rianda on 08/05/2017.
 */
public class Preferences {

  public static final String TOKEN = "token";

  /* --------------------------------------------------- */
  /* > Convenience */
  /* --------------------------------------------------- */
  private static SharedPreferences getPreferences() {
    return PreferenceManager.getDefaultSharedPreferences(ContextProvider.get());
  }

  private static SharedPreferences.Editor getEditor() {
    return getPreferences().edit();
  }

  public static void clear() {
    getEditor().clear().apply();
  }

  public static void clearKey(String key) {
    getEditor().remove(key).apply();
  }

  /* --------------------------------------------------- */
  /* > End Convenience */
  /* --------------------------------------------------- */

  public static Token getToken() {
    return returnObjectOrNull(Token.class, getPreferences().getString(TOKEN, null));
  }

  public static void setToken(Token token) {
    Gson gson = new Gson();
    getEditor().putString(TOKEN, gson.toJson(token)).apply();
  }

  public static <T> T returnObjectOrNull(Class<T> clazz, String data) {
    try {
      Gson gson = new Gson();
      if (data == null) {
        return null;
      } else {
        try {
          return gson.fromJson(data, clazz);
        } catch (Exception e) {
          return null;
        }
      }
    } catch (Exception e) {
      return null;
    }
  }
}
