
package com.sabaindomedika.stscustomer.model.notification;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TicketType implements Parcelable
{

    @SerializedName("data")
    @Expose
    private Data___ data;
    public final static Creator<TicketType> CREATOR = new Creator<TicketType>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TicketType createFromParcel(Parcel in) {
            return new TicketType(in);
        }

        public TicketType[] newArray(int size) {
            return (new TicketType[size]);
        }

    }
    ;

    protected TicketType(Parcel in) {
        this.data = ((Data___) in.readValue((Data___.class.getClassLoader())));
    }

    public TicketType() {
    }

    public Data___ getData() {
        return data;
    }

    public void setData(Data___ data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(data);
    }

    public int describeContents() {
        return  0;
    }

}
