package com.clinic.service;

import com.clinic.config.HikariConnectionPool;
import com.clinic.dao.*;
import com.clinic.dto.Billing;
import com.clinic.dto.VisitHistory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;


public class AppointmentService {

    private final AppointmentDAO  appointmentDAO;
    private final BillingDAO      billingDAO;
    private final VisitHistoryDAO visitHistoryDAO;

    public AppointmentService() {
        this.appointmentDAO  = new AppointmentDAOImpl();
        this.billingDAO      = new BillingDAOImpl();
        this.visitHistoryDAO = new VisitHistoryDAOImpl();
    }

    // Package-private for testing
    AppointmentService(AppointmentDAO a, BillingDAO b, VisitHistoryDAO v) {
        this.appointmentDAO  = a;
        this.billingDAO      = b;
        this.visitHistoryDAO = v;
    }

    /**
     * Atomically completes an appointment:
     *   1. Marks appointment as Completed
     *   2. Creates a Pending bill
     *   3. Records visit diagnosis + prescription + notes
     *
     * @param appointmentId appointment to complete
     * @param amount        bill amount
     * @param diagnosis     doctor's finding
     * @param prescription  what was prescribed (nullable)
     * @param visitNotes    additional notes (nullable) — maps to visit_notes column
     * @return true if all three committed; false if anything failed (fully rolled back)
     */
    public boolean completeAppointment(int appointmentId,
                                       BigDecimal amount,
                                       String diagnosis,
                                       String prescription,
                                       String visitNotes) {
        Connection conn = null;

        try {
            // ── ONE shared connection ─────────────────────────────────────
            conn = HikariConnectionPool.getConnection();
            conn.setAutoCommit(false); // hold all changes until we say commit

            // ── Write 1: Mark appointment Completed ───────────────────────
            // This also fires the audit trigger (trg_appointment_audit_update)
            boolean updated = appointmentDAO.updateAppointmentStatus(appointmentId, "Completed", conn);
            if (!updated) {
                System.err.println("[Service] Appointment ID " + appointmentId + " not found.");
                conn.rollback();
                return false;
            }

            // ── Write 2: Create the bill ───────────────────────────────────
            Billing billing = new Billing(appointmentId, amount);
            billingDAO.insertBilling(billing, conn);

            // ── Write 3: Record the visit ──────────────────────────────────
            VisitHistory visit = new VisitHistory(appointmentId, diagnosis, prescription, visitNotes);
            visitHistoryDAO.insertVisitHistory(visit, conn);

            // ── All 3 succeeded → save permanently ────────────────────────
            conn.commit();
            System.out.println("[Service] Appointment " + appointmentId + " completed successfully.");
            return true;

        } catch (SQLException e) {
            System.err.println("[Service] DB error, rolling back: " + e.getMessage());
            rollbackQuietly(conn);
            return false;

        } catch (RuntimeException e) {
            System.err.println("[Service] Operation failed, rolling back: " + e.getMessage());
            rollbackQuietly(conn);
            return false;

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // restore for pool reuse
                    conn.close();             // return to pool
                } catch (SQLException e) {
                    System.err.println("[Service] Cleanup failed: " + e.getMessage());
                }
            }
        }
    }

    private void rollbackQuietly(Connection conn) {
        if (conn != null) {
            try { conn.rollback(); }
            catch (SQLException ex) {
                System.err.println("[Service] Rollback also failed: " + ex.getMessage());
            }
        }
    }
}
