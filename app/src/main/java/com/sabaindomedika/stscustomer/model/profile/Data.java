
package com.sabaindomedika.stscustomer.model.profile;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("fcm_token")
    @Expose
    private String fcmToken;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("supports")
    @Expose
    private Supports supports;
    @SerializedName("instruments")
    @Expose
    private Instruments instruments;
    @SerializedName("interfaces")
    @Expose
    private Interfaces interfaces;
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
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.phoneNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.fcmToken = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.customer = ((Customer) in.readValue((Customer.class.getClassLoader())));
        this.supports = ((Supports) in.readValue((Supports.class.getClassLoader())));
        this.instruments = ((Instruments) in.readValue((Instruments.class.getClassLoader())));
        this.interfaces = ((Interfaces) in.readValue((Interfaces.class.getClassLoader())));
    }

    public Data() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Supports getSupports() {
        return supports;
    }

    public void setSupports(Supports supports) {
        this.supports = supports;
    }

    public Instruments getInstruments() {
        return instruments;
    }

    public void setInstruments(Instruments instruments) {
        this.instruments = instruments;
    }

    public Interfaces getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Interfaces interfaces) {
        this.interfaces = interfaces;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(email);
        dest.writeValue(name);
        dest.writeValue(phoneNumber);
        dest.writeValue(fcmToken);
        dest.writeValue(type);
        dest.writeValue(customer);
        dest.writeValue(supports);
        dest.writeValue(instruments);
        dest.writeValue(interfaces);
    }

    public int describeContents() {
        return  0;
    }

}
