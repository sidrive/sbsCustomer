
package com.sabaindomedika.stscustomer.model.notification;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class CreatedAt_ implements Parcelable
{

    @SerializedName("date") private String date;
    @SerializedName("timezone_type") private String timezoneType;
    @SerializedName("timezone") private String timeZone;

    protected CreatedAt_(Parcel in) {
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

    public static final Creator<CreatedAt_> CREATOR = new Creator<CreatedAt_>() {
        @Override public CreatedAt_ createFromParcel(Parcel in) {
            return new CreatedAt_(in);
        }

        @Override public CreatedAt_[] newArray(int size) {
            return new CreatedAt_[size];
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
