
package com.sabaindomedika.stscustomer.model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pivot {

    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("employee_id")
    @Expose
    private Integer employeeId;
    @SerializedName("is_supervisor")
    @Expose
    private Boolean isSupervisor;
    @SerializedName("division_id")
    @Expose
    private Integer divisionId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean getIsSupervisor() {
        return isSupervisor;
    }

    public void setIsSupervisor(Boolean isSupervisor) {
        this.isSupervisor = isSupervisor;
    }

    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

}
