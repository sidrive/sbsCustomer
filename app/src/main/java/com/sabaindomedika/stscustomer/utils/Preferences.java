package com.sabaindomedika.stscustomer.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sabaindomedika.stscustomer.model.Department;
import com.sabaindomedika.stscustomer.model.Division;
import com.sabaindomedika.stscustomer.model.Instrument;
import com.sabaindomedika.stscustomer.model.RequestDivision;
import com.sabaindomedika.stscustomer.model.Token;
import com.sabaindomedika.stscustomer.model.User;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Fajar Rianda on 08/05/2017.
 */
public class Preferences {

  private static HashMap<String, List<Instrument>> hashMapInstrument = new HashMap<>();
  private static HashMap<String, List<RequestDivision>> hashMapRequest = new HashMap<>();

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

  /* Instrument */

  public static HashMap<String, List<Instrument>> getHashMapInstrument() {
    HashMap<String, List<Instrument>> map =
        returnListOrNull(new TypeToken<HashMap<String, List<Instrument>>>() {
        }.getType(), getPreferences().getString(Instrument.class.getSimpleName(), null));
    return map;
  }

  public static void setInstrument(String key, List<Instrument> value) {
    hashMapInstrument = getHashMapInstrument() == null
        ? new HashMap<>()
        : getHashMapInstrument();

    hashMapInstrument.put(key, value);

    getEditor().putString(Instrument.class.getSimpleName(), new Gson().toJson(hashMapInstrument))
        .apply();
  }

  public static List<Instrument> getInstrument(String key) {
    return getHashMapInstrument().get(key);
  }

  /* End Instrument */

  /* Request Division */

  public static HashMap<String, List<RequestDivision>> getHashMapRequest() {
    HashMap<String, List<RequestDivision>> map =
        returnListOrNull(new TypeToken<HashMap<String, List<RequestDivision>>>() {
        }.getType(), getPreferences().getString(RequestDivision.class.getSimpleName(), null));
    return map;
  }

  public static void setRequestDvision(String key, List<RequestDivision> value) {

    hashMapRequest = getHashMapRequest() == null
        ? new HashMap<>()
        : getHashMapRequest();

    hashMapRequest.put(key, value);

    getEditor().putString(RequestDivision.class.getSimpleName(), new Gson().toJson(hashMapRequest))
        .apply();
  }

  public static List<RequestDivision> getRequestDivision(String key) {
    return getHashMapRequest().get(key);
  }

  /* End Request Division */

  public static Token getToken() {
    return returnObjectOrNull(Token.class,
        getPreferences().getString(Token.class.getSimpleName(), null));
  }

  public static void setToken(Token token) {
    Gson gson = new Gson();
    getEditor().putString(Token.class.getSimpleName(), gson.toJson(token)).apply();
  }

  public static User getUserProfile() {
    return returnObjectOrNull(User.class,
        getPreferences().getString(User.class.getSimpleName(), null));
  }

  public static void setUserProfile(User user) {
    Gson gson = new Gson();
    getEditor().putString(User.class.getSimpleName(), gson.toJson(user)).apply();
  }

  public static List<Division> getDivision() {
    return returnListOrNull(new TypeToken<List<Division>>() {
    }.getType(), getPreferences().getString(Division.class.getSimpleName(), null));
  }

  public static void setDivision(List<Division> divisions) {
    Gson gson = new Gson();
    getEditor().putString(Division.class.getSimpleName(), gson.toJson(divisions)).apply();
  }

  public static List<Department> getDepartment() {
    return returnListOrNull(new TypeToken<List<Department>>() {
    }.getType(), getPreferences().getString(Department.class.getSimpleName(), null));
  }

  public static void setDepartment(List<Department> departments) {
    Gson gson = new Gson();
    getEditor().putString(Department.class.getSimpleName(), gson.toJson(departments)).apply();
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

  public static <T> T returnListOrNull(Type type, String data) {
    try {
      Gson gson = new Gson();
      if (data == null) {
        return null;
      } else {
        try {
          return gson.fromJson(data, type);
        } catch (Exception e) {
          return null;
        }
      }
    } catch (Exception e) {
      return null;
    }
  }
}
