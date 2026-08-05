package com.clinic.dao;

import com.clinic.config.HikariConnectionPool;
import com.clinic.dto.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AppointmentDAOImpl — SQL for the appointments table.
 *
 * Column mapping (user's schema):
 *   appointment_id, patient_id, doctor_id,
 *   appointment_date DATETIME,   ← single column, no separate time column
 *   status ENUM('Scheduled','Completed','Cancelled')
 */
public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public int insertAppointment(Appointment appointment) {
        String sql = """
            INSERT INTO appointments (patient_id, doctor_id, appointment_date, status)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, appointment.getPatientId());
            pstmt.setInt(2, appointment.getDoctorId());
            pstmt.setTimestamp(3,
                appointment.getAppointmentDate() != null
                    ? Timestamp.valueOf(appointment.getAppointmentDate())
                    : null
            );
            pstmt.setString(4,
                appointment.getStatus() != null ? appointment.getStatus() : "Scheduled"
            );
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("[AppointmentDAO] insertAppointment failed: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        String sql = "SELECT * FROM appointments WHERE appointment_id = ?";

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, appointmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }

        } catch (SQLException e) {
            System.err.println("[AppointmentDAO] getAppointmentById failed: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        String sql = "SELECT * FROM appointments ORDER BY appointment_date DESC";
        List<Appointment> list = new ArrayList<>();

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.err.println("[AppointmentDAO] getAllAppointments failed: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(int patientId) {
        String sql = "SELECT * FROM appointments WHERE patient_id = ? ORDER BY appointment_date DESC";
        List<Appointment> list = new ArrayList<>();

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            System.err.println("[AppointmentDAO] getAppointmentsByPatient failed: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        String sql = "SELECT * FROM appointments WHERE doctor_id = ? ORDER BY appointment_date DESC";
        List<Appointment> list = new ArrayList<>();

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, doctorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            System.err.println("[AppointmentDAO] getAppointmentsByDoctor failed: " + e.getMessage());
        }
        return list;
    }

    /**
     * Takes a shared external connection (used by AppointmentService transaction).
     * Throws RuntimeException on failure to trigger rollback in the service.
     */
    @Override
    public boolean updateAppointmentStatus(int appointmentId, String status, Connection conn) {
        String sql = "UPDATE appointments SET status = ? WHERE appointment_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, appointmentId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[AppointmentDAO] updateAppointmentStatus failed: " + e.getMessage());
            throw new RuntimeException("updateAppointmentStatus failed", e);
        }
    }

    @Override
    public boolean cancelAppointment(int appointmentId) {
        // Only cancel if currently Scheduled — prevents cancelling completed visits
        String sql = "UPDATE appointments SET status = 'Cancelled' WHERE appointment_id = ? AND status = 'Scheduled'";

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, appointmentId);
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                System.err.println("[AppointmentDAO] Cancel failed: not found or already not Scheduled.");
                return false;
            }
            return true;

        } catch (SQLException e) {
            System.err.println("[AppointmentDAO] cancelAppointment failed: " + e.getMessage());
        }
        return false;
    }

    // -------------------------------------------------------
    // mapRow — DATETIME handled as Timestamp → LocalDateTime
    // -------------------------------------------------------
    private Appointment mapRow(ResultSet rs) throws SQLException {
        Appointment a = new Appointment();
        a.setAppointmentId(rs.getInt("appointment_id"));
        a.setPatientId(rs.getInt("patient_id"));
        a.setDoctorId(rs.getInt("doctor_id"));

        // appointment_date is DATETIME in user's schema → Timestamp → LocalDateTime
        Timestamp ts = rs.getTimestamp("appointment_date");
        if (ts != null) a.setAppointmentDate(ts.toLocalDateTime());

        a.setStatus(rs.getString("status"));
        return a;
    }
}
