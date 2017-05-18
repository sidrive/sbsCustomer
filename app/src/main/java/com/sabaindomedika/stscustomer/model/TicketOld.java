package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 04/05/2017.
 */
public class TicketOld implements Parcelable {

  @SerializedName("ticket_nummber") private String ticketNumber;
  @SerializedName("content") private String Content;

  public TicketOld() {
  }

  public String getContent() {
    return Content;
  }

  public void setContent(String content) {
    Content = content;
  }

  public String getTicketNumber() {
    return ticketNumber;
  }

  public void setTicketNumber(String ticketNumber) {
    this.ticketNumber = ticketNumber;
  }

  protected TicketOld(Parcel in) {
    ticketNumber = in.readString();
    Content = in.readString();
  }

  public static final Creator<TicketOld> CREATOR = new Creator<TicketOld>() {
    @Override public TicketOld createFromParcel(Parcel in) {
      return new TicketOld(in);
    }

    @Override public TicketOld[] newArray(int size) {
      return new TicketOld[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(ticketNumber);
    dest.writeString(Content);
  }
}
