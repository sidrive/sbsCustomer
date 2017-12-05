
package com.sabaindomedika.stscustomer.model.notification;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClosedAt implements Parcelable
{

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("timezone_type")
    @Expose
    private Integer timezoneType;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    public final static Creator<ClosedAt> CREATOR = new Creator<ClosedAt>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ClosedAt createFromParcel(Parcel in) {
            return new ClosedAt(in);
        }

        public ClosedAt[] newArray(int size) {
            return (new ClosedAt[size]);
        }

    }
    ;

    protected ClosedAt(Parcel in) {
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.timezoneType = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.timezone = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ClosedAt() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTimezoneType() {
        return timezoneType;
    }

    public void setTimezoneType(Integer timezoneType) {
        this.timezoneType = timezoneType;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(date);
        dest.writeValue(timezoneType);
        dest.writeValue(timezone);
    }

    public int describeContents() {
        return  0;
    }

}
