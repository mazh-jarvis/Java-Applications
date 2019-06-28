package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataTransferObject;

public class Order implements DataTransferObject {

    private long id,
        totalDue,
        customerId,
        salespersonId;
    private String creationDate,
        status;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long orderId) {
        this.id = orderId;
    }

    public long getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(long totalDue) {
        this.totalDue = totalDue;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getSalespersonId() {
        return salespersonId;
    }

    public void setSalespersonId(long salespersonId) {
        this.salespersonId = salespersonId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
