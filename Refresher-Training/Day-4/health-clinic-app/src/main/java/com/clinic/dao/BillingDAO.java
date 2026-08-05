package com.clinic.dao;

import com.clinic.dto.Billing;
import java.sql.Connection;
import java.util.List;

/**
 * BillingDAO — contract for the billing table.
 *
 * User's schema uses bill_id as PK (not billing_id).
 * payment_status ENUM: 'Pending','Paid','Refunded'
 */
public interface BillingDAO {

    /**
     * Insert a bill using a shared transaction connection.
     * Does NOT commit — caller (AppointmentService) owns commit/rollback.
     * @return generated bill_id, or -1 on failure.
     */
    int insertBilling(Billing billing, Connection conn);

    /** @return Billing for the given appointment, or null. */
    Billing getBillingByAppointment(int appointmentId);

    /** @return Billing by its own PK (bill_id). */
    Billing getBillingByBillId(int billId);

    /** Update payment status (Pending → Paid / Refunded). */
    boolean updatePaymentStatus(int billId, String paymentStatus);

    /** @return all billing records, newest first. */
    List<Billing> getAllBillings();
}
