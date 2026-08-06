package com.clinic.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Billing {

    private int           billId;         // column: bill_id (PK)
    private int           appointmentId;
    private BigDecimal    amount;
    private String        paymentStatus;  // Pending | Paid | Refunded
    private LocalDateTime billingDate;

    // -------------------------------------------------------
    // Constructors
    // -------------------------------------------------------

    public Billing() {}

    /** Used by AppointmentService when creating a bill atomically. */
    public Billing(int appointmentId, BigDecimal amount) {
        this.appointmentId = appointmentId;
        this.amount        = amount;
        this.paymentStatus = "Pending";
    }

    // -------------------------------------------------------
    // Getters & Setters
    // -------------------------------------------------------

    public int getBillId()              { return billId; }
    public void setBillId(int billId)   { this.billId = billId; }

    public int getAppointmentId()                   { return appointmentId; }
    public void setAppointmentId(int id)            { this.appointmentId = id; }

    public BigDecimal getAmount()                   { return amount; }
    public void setAmount(BigDecimal amount)        { this.amount = amount; }

    public String getPaymentStatus()                        { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus)      { this.paymentStatus = paymentStatus; }

    public LocalDateTime getBillingDate()                   { return billingDate; }
    public void setBillingDate(LocalDateTime billingDate)   { this.billingDate = billingDate; }

    @Override
    public String toString() {
        return String.format(
            "Billing[BillID=%-4d | Appt#%-4d | Amount: ₹%-10.2f | Status: %-10s | Date: %s]",
            billId, appointmentId, amount, paymentStatus,
            (billingDate != null ? billingDate.toLocalDate() : "N/A")
        );
    }
}
