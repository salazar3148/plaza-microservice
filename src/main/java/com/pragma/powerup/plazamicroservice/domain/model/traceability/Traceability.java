package com.pragma.powerup.plazamicroservice.domain.model.traceability;

import java.time.LocalDateTime;

public class Traceability {
    private Long orderId;
    private Long customerId;
    private String customerMail;
    private LocalDateTime date;
    private String oldStatus;
    private String newStatus;
    private Long employeeId;
    private String employeeMail;

    public Traceability(Long orderId, Long customerId, String customerMail, LocalDateTime date, String oldStatus, String newStatus, Long employeeId, String employeeMail) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerMail = customerMail;
        this.date = date;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.employeeId = employeeId;
        this.employeeMail = employeeMail;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getOldStatus() {
        return oldStatus;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeMail() {
        return employeeMail;
    }
}
