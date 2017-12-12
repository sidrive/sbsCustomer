
package com.sabaindomedika.stscustomer.model.profile;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Interfaces implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum__> data = null;
    public final static Creator<Interfaces> CREATOR = new Creator<Interfaces>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Interfaces createFromParcel(Parcel in) {
            return new Interfaces(in);
        }

        public Interfaces[] newArray(int size) {
            return (new Interfaces[size]);
        }

    }
    ;

    protected Interfaces(Parcel in) {
        in.readList(this.data, (com.sabaindomedika.stscustomer.model.profile.Datum__.class.getClassLoader()));
    }

    public Interfaces() {
    }

    public List<Datum__> getData() {
        return data;
    }

    public void setData(List<Datum__> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
