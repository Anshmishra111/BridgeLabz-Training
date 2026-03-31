package BridgeLab_Training.Jdbc.dao;

import BridgeLab_Training.Jdbc.config.DBConnection;
import BridgeLab_Training.Jdbc.model.Prescription;
import BridgeLab_Training.Jdbc.model.Visit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitDAOImpl implements VisitDAO {

    @Override
    public int recordVisit(Visit visit) {
        String sql = """
            INSERT INTO visits (appointment_id, diagnosis, notes)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, visit.getAppointmentId());
            ps.setString(2, visit.getDiagnosis());
            ps.setString(3, visit.getNotes());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException("Error recording visit", e);
        }
    }

    @Override
    public void updateAppointmentStatus(int appointmentId, String status) {
        String sql = "UPDATE appointments SET status=? WHERE appointment_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, appointmentId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating appointment", e);
        }
    }

    @Override
    public List<String> getMedicalHistory(int patientId) {
        String sql = """
            SELECT v.visit_date, d.name, v.diagnosis,
                   pr.medicine_name, pr.dosage, pr.duration
            FROM visits v
            JOIN appointments a ON v.appointment_id = a.appointment_id
            JOIN doctors d ON a.doctor_id = d.doctor_id
            LEFT JOIN prescriptions pr ON v.visit_id = pr.visit_id
            WHERE a.patient_id = ?
            ORDER BY v.visit_date DESC
        """;

        List<String> history = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                history.add(
                    rs.getDate(1) + " | " +
                    rs.getString(2) + " | " +
                    rs.getString(3) + " | " +
                    rs.getString(4)
                );
            }
            return history;

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching history", e);
        }
    }

    @Override
    public void addPrescriptions(List<Prescription> prescriptions) {
        String sql = """
            INSERT INTO prescriptions
            (visit_id, medicine_name, dosage, duration)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            for (Prescription p : prescriptions) {
                ps.setInt(1, p.getVisitId());
                ps.setString(2, p.getMedicineName());
                ps.setString(3, p.getDosage());
                ps.setString(4, p.getDuration());
                ps.addBatch();
            }

            ps.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            throw new RuntimeException("Error adding prescriptions", e);
        }
    }
}