package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 18/05/2017.
 */
public class Ticket extends Responses implements Parcelable {

  @SerializedName("id") private String id;
  @SerializedName("ticket_type_id") private String ticketTypeId;
  @SerializedName("division_id") private String divisionId;
  @SerializedName("request_id") private String requestId;
  @SerializedName("department_id") private String departmentId;
  @SerializedName("priority") private String priority;
  @SerializedName("description") private String description;
  @SerializedName("staff_name") private String staffName;
  @SerializedName("staff_phone_number") private String staffPhoneNumber;

  public Ticket() {

  }

  protected Ticket(Parcel in) {
    id = in.readString();
    ticketTypeId = in.readString();
    divisionId = in.readString();
    requestId = in.readString();
    departmentId = in.readString();
    priority = in.readString();
    description = in.readString();
    staffName = in.readString();
    staffPhoneNumber = in.readString();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(ticketTypeId);
    dest.writeString(divisionId);
    dest.writeString(requestId);
    dest.writeString(departmentId);
    dest.writeString(priority);
    dest.writeString(description);
    dest.writeString(staffName);
    dest.writeString(staffPhoneNumber);
  }

  @Override public int describeContents() {
    return 0;
  }

  public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
    @Override public Ticket createFromParcel(Parcel in) {
      return new Ticket(in);
    }

    @Override public Ticket[] newArray(int size) {
      return new Ticket[size];
    }
  };

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDivisionId() {
    return divisionId;
  }

  public void setDivisionId(String divisionId) {
    this.divisionId = divisionId;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(String departmentId) {
    this.departmentId = departmentId;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public String getStaffName() {
    return staffName;
  }

  public void setStaffName(String staffName) {
    this.staffName = staffName;
  }

  public String getStaffPhoneNumber() {
    return staffPhoneNumber;
  }

  public void setStaffPhoneNumber(String staffPhoneNumber) {
    this.staffPhoneNumber = staffPhoneNumber;
  }

  public String getTicketTypeId() {
    return ticketTypeId;
  }

  public void setTicketTypeId(String ticketTypeId) {
    this.ticketTypeId = ticketTypeId;
  }
}