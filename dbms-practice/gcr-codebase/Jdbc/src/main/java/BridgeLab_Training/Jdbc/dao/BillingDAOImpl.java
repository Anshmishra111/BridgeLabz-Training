package BridgeLab_Training.Jdbc.dao;

import BridgeLab_Training.Jdbc.config.DBConnection;
import BridgeLab_Training.Jdbc.model.PaymentTransaction;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillingDAOImpl implements BillingDAO {

    @Override
    public double getConsultationFeeByVisit(int visitId) {
        String sql = """
            SELECT d.consultation_fee
            FROM visits v
            JOIN appointments a ON v.appointment_id = a.appointment_id
            JOIN doctors d ON a.doctor_id = d.doctor_id
            WHERE v.visit_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, visitId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getDouble(1);

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching consultation fee", e);
        }
    }

    @Override
    public int generateBill(int visitId, double totalAmount) {
        String sql = """
            INSERT INTO bills (visit_id, total_amount, payment_status)
            VALUES (?, ?, 'UNPAID')
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, visitId);
            ps.setDouble(2, totalAmount);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException("Error generating bill", e);
        }
    }

    @Override
    public void markBillPaid(int billId) {
        String sql = "UPDATE bills SET payment_status='PAID' WHERE bill_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, billId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating bill status", e);
        }
    }

    @Override
    public void insertPayment(PaymentTransaction payment) {
        String sql = """
            INSERT INTO payment_transactions (bill_id, payment_mode, amount)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, payment.getBillId());
            ps.setString(2, payment.getPaymentMode());
            ps.setDouble(3, payment.getAmount());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting payment", e);
        }
    }

    @Override
    public List<String> getOutstandingBills() {
        String sql = """
            SELECT p.name, COUNT(b.bill_id), SUM(b.total_amount)
            FROM bills b
            JOIN visits v ON b.visit_id = v.visit_id
            JOIN appointments a ON v.appointment_id = a.appointment_id
            JOIN patients p ON a.patient_id = p.patient_id
            WHERE b.payment_status = 'UNPAID'
            GROUP BY p.patient_id
        """;

        List<String> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(
                    rs.getString(1) + " | Bills: " +
                    rs.getInt(2) + " | Due: " +
                    rs.getDouble(3)
                );
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching outstanding bills", e);
        }
    }

    @Override
    public List<String> getRevenueReport(LocalDate from, LocalDate to) {
        String sql = """
            SELECT d.name, SUM(b.total_amount)
            FROM bills b
            JOIN visits v ON b.visit_id = v.visit_id
            JOIN appointments a ON v.appointment_id = a.appointment_id
            JOIN doctors d ON a.doctor_id = d.doctor_id
            WHERE b.payment_status='PAID'
              AND b.created_at BETWEEN ? AND ?
            GROUP BY d.doctor_id
            HAVING SUM(b.total_amount) > 0
        """;

        List<String> report = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(from));
            ps.setDate(2, Date.valueOf(to));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                report.add(rs.getString(1) + " | Revenue: " + rs.getDouble(2));
            }
            return report;

        } catch (SQLException e) {
            throw new RuntimeException("Error generating revenue report", e);
        }
    }
}