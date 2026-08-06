package com.clinic.dao;

import com.clinic.config.HikariConnectionPool;
import com.clinic.dto.VisitHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class VisitHistoryDAOImpl implements VisitHistoryDAO {

    /**
     * Uses shared connection from AppointmentService transaction.
     * Throws RuntimeException on failure to trigger rollback.
     */
    @Override
    public int insertVisitHistory(VisitHistory visitHistory, Connection conn) {
        String sql = """
            INSERT INTO visit_history (appointment_id, diagnosis, prescription, visit_notes)
            VALUES (?, ?, ?, ?)
            """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, visitHistory.getAppointmentId());
            pstmt.setString(2, visitHistory.getDiagnosis());
            pstmt.setString(3, visitHistory.getPrescription());
            pstmt.setString(4, visitHistory.getVisitNotes());   // ← user's visit_notes column
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("[VisitHistoryDAO] insertVisitHistory failed: " + e.getMessage());
            throw new RuntimeException("insertVisitHistory failed", e); // triggers rollback
        }
        return -1;
    }

    @Override
    public VisitHistory getVisitByAppointment(int appointmentId) {
        String sql = "SELECT * FROM visit_history WHERE appointment_id = ?";

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, appointmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }

        } catch (SQLException e) {
            System.err.println("[VisitHistoryDAO] getVisitByAppointment failed: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<VisitHistory> getVisitsByPatient(int patientId) {
        // JOIN through appointments because visit_history has no patient_id column
        String sql = """
            SELECT vh.*
              FROM visit_history vh
              JOIN appointments a ON vh.appointment_id = a.appointment_id
             WHERE a.patient_id = ?
             ORDER BY vh.visit_id DESC
            """;
        List<VisitHistory> list = new ArrayList<>();

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            System.err.println("[VisitHistoryDAO] getVisitsByPatient failed: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<VisitHistory> getAllVisitHistory() {
        String sql = "SELECT * FROM visit_history ORDER BY visit_id DESC";
        List<VisitHistory> list = new ArrayList<>();

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.err.println("[VisitHistoryDAO] getAllVisitHistory failed: " + e.getMessage());
        }
        return list;
    }

    // -------------------------------------------------------
    // mapRow — includes visit_notes from user's schema
    // -------------------------------------------------------
    private VisitHistory mapRow(ResultSet rs) throws SQLException {
        VisitHistory v = new VisitHistory();
        v.setVisitId(rs.getInt("visit_id"));
        v.setAppointmentId(rs.getInt("appointment_id"));
        v.setDiagnosis(rs.getString("diagnosis"));
        v.setPrescription(rs.getString("prescription"));
        v.setVisitNotes(rs.getString("visit_notes"));   // ← user's column
        return v;
    }
}
