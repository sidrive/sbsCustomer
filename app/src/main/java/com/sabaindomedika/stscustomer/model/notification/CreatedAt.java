
package com.sabaindomedika.stscustomer.model.notification;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatedAt implements Parcelable
{

    @SerializedName("date") private String date;
    @SerializedName("timezone_type") private String timezoneType;
    @SerializedName("timezone") private String timeZone;

    protected CreatedAt(Parcel in) {
        date = in.readString();
        timezoneType = in.readString();
        timeZone = in.readString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getTimezoneType() {
        return timezoneType;
    }

    public void setTimezoneType(String timezoneType) {
        this.timezoneType = timezoneType;
    }

    public static final Creator<CreatedAt> CREATOR = new Creator<CreatedAt>() {
        @Override public CreatedAt createFromParcel(Parcel in) {
            return new CreatedAt(in);
        }

        @Override public CreatedAt[] newArray(int size) {
            return new CreatedAt[size];
        }
    };

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(timezoneType);
        dest.writeString(timeZone);
    }
}
