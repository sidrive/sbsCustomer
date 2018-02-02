package com.sabaindomedika.stscustomer.model.broadcast;

import android.os.Parcelable;
import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ikun on 02/02/18.
 */

public class ResponseBroadcast implements Parcelable{

    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public final static Creator<ResponseBroadcast> CREATOR = new Creator<ResponseBroadcast>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ResponseBroadcast createFromParcel(Parcel in) {
            return new ResponseBroadcast(in);
        }

        public ResponseBroadcast[] newArray(int size) {
            return (new ResponseBroadcast[size]);
        }

    };

    protected ResponseBroadcast(Parcel in) {
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (com.sabaindomedika.stscustomer.model.broadcast.Data.class.getClassLoader()));
    }

    public ResponseBroadcast() {
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(statusCode);
        dest.writeValue(success);
        dest.writeValue(message);
        dest.writeValue(data);
    }

    public int describeContents() {
        return  0;
    }
}
