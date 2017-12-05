
package com.sabaindomedika.stscustomer.model.notification;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sabaindomedika.stscustomer.model.notification.Ticket;

public class Datum implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("is_read")
    @Expose
    private Boolean isRead;
    @SerializedName("created_at")
    @Expose
    private CreatedAt createdAt;
    @SerializedName("ticket")
    @Expose
    private Ticket ticket;
    public final static Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    }
    ;

    protected Datum(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.body = ((String) in.readValue((String.class.getClassLoader())));
        this.isRead = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.createdAt = ((CreatedAt) in.readValue((CreatedAt.class.getClassLoader())));
        this.ticket = ((Ticket) in.readValue((Ticket.class.getClassLoader())));
    }

    public Datum() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public CreatedAt getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(CreatedAt createdAt) {
        this.createdAt = createdAt;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(body);
        dest.writeValue(isRead);
        dest.writeValue(createdAt);
        dest.writeValue(ticket);
    }

    public int describeContents() {
        return  0;
    }

}
