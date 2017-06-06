package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 06/06/2017.
 */
public class Instrument implements Parcelable{

  @SerializedName("id") private String id;
  @SerializedName("serial_number") private String serialNumber;
  @SerializedName("instrumentType") private Content<InstrumentType> instrumentType;
  @SerializedName("instrumentCategory") private Content<InstrumentCategory> instrumentCategory;

  protected Instrument(Parcel in) {
    id = in.readString();
    serialNumber = in.readString();
    instrumentType = in.readParcelable(Content.class.getClassLoader());
    instrumentCategory = in.readParcelable(Content.class.getClassLoader());
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(serialNumber);
    dest.writeParcelable(instrumentType, flags);
    dest.writeParcelable(instrumentCategory, flags);
  }

  @Override public int describeContents() {
    return 0;
  }

  public static final Creator<Instrument> CREATOR = new Creator<Instrument>() {
    @Override public Instrument createFromParcel(Parcel in) {
      return new Instrument(in);
    }

    @Override public Instrument[] newArray(int size) {
      return new Instrument[size];
    }
  };

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Content<InstrumentCategory> getInstrumentCategory() {
    return instrumentCategory;
  }

  public void setInstrumentCategory(Content<InstrumentCategory> instrumentCategory) {
    this.instrumentCategory = instrumentCategory;
  }

  public Content<InstrumentType> getInstrumentType() {
    return instrumentType;
  }

  public void setInstrumentType(Content<InstrumentType> instrumentType) {
    this.instrumentType = instrumentType;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }
}

