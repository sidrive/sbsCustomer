
package com.sabaindomedika.stscustomer.model.notification;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer implements Parcelable
{

    @SerializedName("data")
    @Expose
    private Data__ data;
    public final static Creator<Customer> CREATOR = new Creator<Customer>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        public Customer[] newArray(int size) {
            return (new Customer[size]);
        }

    }
    ;

    protected Customer(Parcel in) {
        this.data = ((Data__) in.readValue((Data__.class.getClassLoader())));
    }

    public Customer() {
    }

    public Data__ getData() {
        return data;
    }

    public void setData(Data__ data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(data);
    }

    public int describeContents() {
        return  0;
    }

}
