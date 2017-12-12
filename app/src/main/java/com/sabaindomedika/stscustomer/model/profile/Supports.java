
package com.sabaindomedika.stscustomer.model.profile;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Supports implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Creator<Supports> CREATOR = new Creator<Supports>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Supports createFromParcel(Parcel in) {
            return new Supports(in);
        }

        public Supports[] newArray(int size) {
            return (new Supports[size]);
        }

    }
    ;

    protected Supports(Parcel in) {
        in.readList(this.data, (com.sabaindomedika.stscustomer.model.profile.Datum.class.getClassLoader()));
    }

    public Supports() {
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
