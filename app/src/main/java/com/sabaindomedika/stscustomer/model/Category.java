package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by raka on 13/12/17.
 */

public class Category implements Parcelable {

  @SerializedName("category")
  @Expose
  private String category;
  public final static Parcelable.Creator<Category> CREATOR = new Creator<Category>() {


    @SuppressWarnings({
        "unchecked"
    })
    public Category createFromParcel(Parcel in) {
      return new Category(in);
    }

    public Category[] newArray(int size) {
      return (new Category[size]);
    }

  }
      ;

  protected Category(Parcel in) {
    this.category = ((String) in.readValue((String.class.getClassLoader())));
  }

  public Category() {
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(category);
  }

  public int describeContents() {
    return 0;
  }
}
