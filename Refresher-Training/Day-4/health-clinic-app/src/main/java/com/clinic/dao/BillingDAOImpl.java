package com.clinic.dao;

import com.clinic.config.HikariConnectionPool;
import com.clinic.dto.Billing;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BillingDAOImpl — SQL for the billing table.
 *
 * Column mapping (user's schema):
 *   bill_id (PK), appointment_id (UNIQUE), amount,
 *   payment_status ENUM('Pending','Paid','Refunded'),
 *   billing_date
 */
public class BillingDAOImpl implements BillingDAO {

    /**
     * Uses an externally provided shared connection — part of the
     * AppointmentService 3-write transaction.
     * Throws RuntimeException on failure to trigger rollback.
     */
    @Override
    public int insertBilling(Billing billing, Connection conn) {
        String sql = """
            INSERT INTO billing (appointment_id, amount, payment_status)
            VALUES (?, ?, ?)
            """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, billing.getAppointmentId());
            pstmt.setBigDecimal(2, billing.getAmount());
            pstmt.setString(3,
                billing.getPaymentStatus() != null ? billing.getPaymentStatus() : "Pending"
            );
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("[BillingDAO] insertBilling failed: " + e.getMessage());
            throw new RuntimeException("insertBilling failed", e); // triggers rollback
        }
        return -1;
    }

    @Override
    public Billing getBillingByAppointment(int appointmentId) {
        String sql = "SELECT * FROM billing WHERE appointment_id = ?";

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, appointmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }

        } catch (SQLException e) {
            System.err.println("[BillingDAO] getBillingByAppointment failed: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Billing getBillingByBillId(int billId) {
        String sql = "SELECT * FROM billing WHERE bill_id = ?";

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, billId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }

        } catch (SQLException e) {
            System.err.println("[BillingDAO] getBillingByBillId failed: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updatePaymentStatus(int billId, String paymentStatus) {
        String sql = "UPDATE billing SET payment_status = ? WHERE bill_id = ?";

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, paymentStatus);
            pstmt.setInt(2, billId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[BillingDAO] updatePaymentStatus failed: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Billing> getAllBillings() {
        String sql = "SELECT * FROM billing ORDER BY billing_date DESC";
        List<Billing> list = new ArrayList<>();

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.err.println("[BillingDAO] getAllBillings failed: " + e.getMessage());
        }
        return list;
    }

    // -------------------------------------------------------
    // mapRow — bill_id is the PK column name in user's schema
    // -------------------------------------------------------
    private Billing mapRow(ResultSet rs) throws SQLException {
        Billing b = new Billing();
        b.setBillId(rs.getInt("bill_id"));                    // ← user's PK name
        b.setAppointmentId(rs.getInt("appointment_id"));

        BigDecimal amount = rs.getBigDecimal("amount");
        b.setAmount(amount != null ? amount : BigDecimal.ZERO);

        b.setPaymentStatus(rs.getString("payment_status"));

        Timestamp ts = rs.getTimestamp("billing_date");
        if (ts != null) b.setBillingDate(ts.toLocalDateTime());

        return b;
    }
}
