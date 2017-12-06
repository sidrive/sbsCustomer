package com.sabaindomedika.stscustomer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 18/05/2017.
 */
public class Ticket implements Parcelable {

  @SerializedName("id") private String id;
  @SerializedName("number") private String number;
  @SerializedName("ticket_type_id") private String ticketTypeId;
  @SerializedName("status") private String status;
  @SerializedName("division_id") private String divisionId;
  @SerializedName("request_id") private String requestId;
  @SerializedName("instrument_id") private String instrumentId;
  @SerializedName("department_id") private String departmentId;
  @SerializedName("interface_id") private String interfaceid;
  @SerializedName("priority") private String priority;
  @SerializedName("description") private String description;
  @SerializedName("staff_name") private String staffName;
  @SerializedName("staff_phone_number") private String staffPhoneNumber;
  @SerializedName("created_at") private Times times;
  @SerializedName("department") private Content<Department> department;
  @SerializedName("division") private Content<Division> division;
  @SerializedName("ticketType") private Content<TicketType> ticketType;

  /* For Close Ticket */
  @SerializedName("rating") private float rating;
  @SerializedName("comment") private String comment;

  public Ticket() {

  }

  protected Ticket(Parcel in) {
    id = in.readString();
    number = in.readString();
    ticketTypeId = in.readString();
    status = in.readString();
    divisionId = in.readString();
    requestId = in.readString();
    instrumentId = in.readString();
    departmentId = in.readString();
    interfaceid = in.readString();
    priority = in.readString();
    description = in.readString();
    staffName = in.readString();
    staffPhoneNumber = in.readString();
    times = in.readParcelable(Times.class.getClassLoader());
    department = in.readParcelable(Content.class.getClassLoader());
    division = in.readParcelable(Content.class.getClassLoader());
    ticketType = in.readParcelable(Content.class.getClassLoader());
    rating = in.readFloat();
    comment = in.readString();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(number);
    dest.writeString(ticketTypeId);
    dest.writeString(status);
    dest.writeString(divisionId);
    dest.writeString(requestId);
    dest.writeString(instrumentId);
    dest.writeString(departmentId);
    dest.writeString(interfaceid);
    dest.writeString(priority);
    dest.writeString(description);
    dest.writeString(staffName);
    dest.writeString(staffPhoneNumber);
    dest.writeParcelable(times, flags);
    dest.writeParcelable(department, flags);
    dest.writeParcelable(division, flags);
    dest.writeParcelable(ticketType, flags);
    dest.writeFloat(rating);
    dest.writeString(comment);
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

  public String getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(String departmentId) {
    this.departmentId = departmentId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getInterfaceid() {
    return interfaceid;
  }

  public void setInterfaceid(String deviceName) {
    this.interfaceid = deviceName;
  }

  public String getDivisionId() {
    return divisionId;
  }

  public void setDivisionId(String divisionId) {
    this.divisionId = divisionId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getInstrumentId() {
    return instrumentId;
  }

  public void setInstrumentId(String instrumentId) {
    this.instrumentId = instrumentId;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Times getTimes() {
    return times;
  }

  public void setTimes(Times times) {
    this.times = times;
  }

  public Content<Department> getDepartment() {
    return department;
  }

  public void setDepartment(Content<Department> department) {
    this.department = department;
  }

  public Content<TicketType> getTicketType() {
    return ticketType;
  }

  public void setTicketType(Content<TicketType> ticketType) {
    this.ticketType = ticketType;
  }

  public Content<Division> getDivision() {
    return division;
  }

  public void setDivision(Content<Division> division) {
    this.division = division;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }
}
