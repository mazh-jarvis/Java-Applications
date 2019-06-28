package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataTransferObject;

import java.math.BigDecimal;
import java.sql.Date;

public class Order implements DataTransferObject {

    private long id;
    private BigDecimal totalDue;
    private Date creationDate;
    private String customerFName,
    customerLName,
    customerEmail,
    salesFName,
    salesLName,
    salesEmail,
    status;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long orderId) {
        this.id = orderId;
    }

    public BigDecimal getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(BigDecimal totalDue) {
        this.totalDue = totalDue;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerFName() {
        return customerFName;
    }

    public void setCustomerFName(String customerFName) {
        this.customerFName = customerFName;
    }

    public String getCustomerLName() {
        return customerLName;
    }

    public void setCustomerLName(String customerLName) {
        this.customerLName = customerLName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getSalesFName() {
        return salesFName;
    }

    public void setSalesFName(String salesFName) {
        this.salesFName = salesFName;
    }

    public String getSalesLName() {
        return salesLName;
    }

    public void setSalesLName(String salesLName) {
        this.salesLName = salesLName;
    }

    public String getSalesEmail() {
        return salesEmail;
    }

    public void setSalesEmail(String salesEmail) {
        this.salesEmail = salesEmail;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalDue=" + totalDue +
                ", creationDate=" + creationDate +
                ", customerFName='" + customerFName + '\'' +
                ", customerLName='" + customerLName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", salesFName='" + salesFName + '\'' +
                ", salesLName='" + salesLName + '\'' +
                ", salesEmail='" + salesEmail + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
