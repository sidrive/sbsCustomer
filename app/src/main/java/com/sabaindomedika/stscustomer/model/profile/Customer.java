
package com.sabaindomedika.stscustomer.model.profile;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer implements Parcelable
{

    @SerializedName("data")
    @Expose
    private Data_ data;
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
        this.data = ((Data_) in.readValue((Data_.class.getClassLoader())));
    }

    public Customer() {
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
