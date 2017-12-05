
package com.sabaindomedika.stscustomer.model.notification;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sabaindomedika.stscustomer.model.notification.TicketType;

public class Data implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("priority")
    @Expose
    private String priority;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("status_text")
    @Expose
    private String statusText;
    @SerializedName("is_read")
    @Expose
    private Boolean isRead;
    @SerializedName("is_closed")
    @Expose
    private Boolean isClosed;
    @SerializedName("travel_time")
    @Expose
    private String travelTime;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("request")
    @Expose
    private String request;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("invoice")
    @Expose
    private String invoice;
    @SerializedName("instrument")
    @Expose
    private Instrument instrument;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("staff_name")
    @Expose
    private String staffName;
    @SerializedName("staff_phone_number")
    @Expose
    private String staffPhoneNumber;
    @SerializedName("closed_at")
    @Expose
    private ClosedAt closedAt;
    @SerializedName("created_at")
    @Expose
    private CreatedAt_ createdAt;
    @SerializedName("ticket_activity_id")
    @Expose
    private String ticketActivityId;
    @SerializedName("activity_id")
    @Expose
    private Integer activityId;
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("ticketType")
    @Expose
    private TicketType ticketType;
    public final static Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;

    protected Data(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.number = ((String) in.readValue((String.class.getClassLoader())));
        this.priority = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.statusText = ((String) in.readValue((String.class.getClassLoader())));
        this.isRead = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.isClosed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.travelTime = ((String) in.readValue((String.class.getClassLoader())));
        this.customerName = ((String) in.readValue((String.class.getClassLoader())));
        this.request = ((String) in.readValue((String.class.getClassLoader())));
        this.comment = ((String) in.readValue((String.class.getClassLoader())));
        this.invoice = ((String) in.readValue((String.class.getClassLoader())));
        this.instrument = ((Instrument) in.readValue((Instrument.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.staffName = ((String) in.readValue((String.class.getClassLoader())));
        this.staffPhoneNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.closedAt = ((ClosedAt) in.readValue((ClosedAt.class.getClassLoader())));
        this.createdAt = ((CreatedAt_) in.readValue((CreatedAt_.class.getClassLoader())));
        this.ticketActivityId = ((String) in.readValue((String.class.getClassLoader())));
        this.activityId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.customer = ((Customer) in.readValue((Customer.class.getClassLoader())));
        this.ticketType = ((TicketType) in.readValue((TicketType.class.getClassLoader())));
    }

    public Data() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ClosedAt getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(ClosedAt closedAt) {
        this.closedAt = closedAt;
    }

    public CreatedAt_ getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(CreatedAt_ createdAt) {
        this.createdAt = createdAt;
    }

    public String getTicketActivityId() {
        return ticketActivityId;
    }

    public void setTicketActivityId(String ticketActivityId) {
        this.ticketActivityId = ticketActivityId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(number);
        dest.writeValue(priority);
        dest.writeValue(status);
        dest.writeValue(statusText);
        dest.writeValue(isRead);
        dest.writeValue(isClosed);
        dest.writeValue(travelTime);
        dest.writeValue(customerName);
        dest.writeValue(request);
        dest.writeValue(comment);
        dest.writeValue(invoice);
        dest.writeValue(instrument);
        dest.writeValue(description);
        dest.writeValue(staffName);
        dest.writeValue(staffPhoneNumber);
        dest.writeValue(closedAt);
        dest.writeValue(createdAt);
        dest.writeValue(ticketActivityId);
        dest.writeValue(activityId);
        dest.writeValue(customer);
        dest.writeValue(ticketType);
    }

    public int describeContents() {
        return  0;
    }

}
