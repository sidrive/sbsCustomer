
package com.sabaindomedika.stscustomer.model.notification;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Instrument implements Parcelable
{

    @SerializedName("data")
    @Expose
    private Data_ data;
    public final static Creator<Instrument> CREATOR = new Creator<Instrument>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Instrument createFromParcel(Parcel in) {
            return new Instrument(in);
        }

        public Instrument[] newArray(int size) {
            return (new Instrument[size]);
        }

    }
    ;

    protected Instrument(Parcel in) {
        this.data = ((Data_) in.readValue((Data_.class.getClassLoader())));
    }

    public Instrument() {
    }

    public Data_ getData() {
        return data;
    }

    public void setData(Data_ data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(data);
    }

    public int describeContents() {
        return  0;
    }

}
