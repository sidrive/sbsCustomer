package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by raka on 07/12/17.
 */

public class Interface implements Parcelable {

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("name")
  @Expose
  private String name;
  public final static Parcelable.Creator<Interface> CREATOR = new Creator<Interface>() {


    @SuppressWarnings({
        "unchecked"
    })
    public Interface createFromParcel(Parcel in) {
      return new Interface(in);
    }

    public Interface[] newArray(int size) {
      return (new Interface[size]);
    }

  }
      ;

  protected Interface(Parcel in) {
    this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
    this.name = ((String) in.readValue((String.class.getClassLoader())));
  }

  public Interface() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(id);
    dest.writeValue(name);
  }

  public int describeContents() {
    return 0;
  }

}
