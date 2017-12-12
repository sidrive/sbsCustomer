package com.sabaindomedika.stscustomer.model.profile;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum__ implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    public final static Parcelable.Creator<Datum__> CREATOR = new Creator<Datum__>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum__ createFromParcel(Parcel in) {
            return new Datum__(in);
        }

        public Datum__[] newArray(int size) {
            return (new Datum__[size]);
        }

    }
        ;

    protected Datum__(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Datum__() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(code);
    }

    public int describeContents() {
        return 0;
    }

}