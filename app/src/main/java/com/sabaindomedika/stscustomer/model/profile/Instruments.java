
package com.sabaindomedika.stscustomer.model.profile;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Instruments implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum_> data = null;
    public final static Creator<Instruments> CREATOR = new Creator<Instruments>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Instruments createFromParcel(Parcel in) {
            return new Instruments(in);
        }

        public Instruments[] newArray(int size) {
            return (new Instruments[size]);
        }

    }
    ;

    protected Instruments(Parcel in) {
        in.readList(this.data, (com.sabaindomedika.stscustomer.model.profile.Datum_.class.getClassLoader()));
    }

    public Instruments() {
    }

    public List<Datum_> getData() {
        return data;
    }

    public void setData(List<Datum_> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
