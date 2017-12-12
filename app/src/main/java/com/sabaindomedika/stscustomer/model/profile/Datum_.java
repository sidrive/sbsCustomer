
package com.sabaindomedika.stscustomer.model.profile;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum_ implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("serial_number")
    @Expose
    private String serialNumber;
    @SerializedName("instrument type id")
    @Expose
    private Integer instrumentTypeId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("contract_type")
    @Expose
    private String contractType;
    public final static Creator<Datum_> CREATOR = new Creator<Datum_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum_ createFromParcel(Parcel in) {
            return new Datum_(in);
        }

        public Datum_[] newArray(int size) {
            return (new Datum_[size]);
        }

    }
    ;

    protected Datum_(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.serialNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.instrumentTypeId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.contractType = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Datum_() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getInstrumentTypeId() {
        return instrumentTypeId;
    }

    public void setInstrumentTypeId(Integer instrumentTypeId) {
        this.instrumentTypeId = instrumentTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(serialNumber);
        dest.writeValue(instrumentTypeId);
        dest.writeValue(type);
        dest.writeValue(contractType);
    }

    public int describeContents() {
        return  0;
    }

}
