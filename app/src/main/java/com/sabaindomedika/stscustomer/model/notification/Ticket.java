
package com.sabaindomedika.stscustomer.model.notification;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticket implements Parcelable
{

    @SerializedName("data")
    @Expose
    private Data data;
    public final static Creator<Ticket> CREATOR = new Creator<Ticket>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        public Ticket[] newArray(int size) {
            return (new Ticket[size]);
        }

    }
    ;

    protected Ticket(Parcel in) {
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    public Ticket() {
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(data);
    }

    public int describeContents() {
        return  0;
    }

}
