package com.clinic.dao;

import com.clinic.config.HikariConnectionPool;
import com.clinic.dto.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PatientDAOImpl implements PatientDAO {

    @Override
    public int insertPatient(Patient patient) {
        String sql = """
            INSERT INTO patients
                (first_name, last_name, date_of_birth, gender, phone_number, email, is_active)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, patient.getFirstName());
            pstmt.setString(2, patient.getLastName());

            if (patient.getDateOfBirth() != null) {
                pstmt.setDate(3, Date.valueOf(patient.getDateOfBirth()));
            } else {
                pstmt.setNull(3, Types.DATE);
            }

            pstmt.setString(4, patient.getGender());
            pstmt.setString(5, patient.getPhoneNumber());
            pstmt.setString(6, patient.getEmail());
            pstmt.setBoolean(7, patient.isActive());

            int affected = pstmt.executeUpdate();
            if (affected == 0) return -1;

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("[PatientDAO] insertPatient failed: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public Patient getPatientById(int patientId) {
        String sql = "SELECT * FROM patients WHERE patient_id = ?";

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }

        } catch (SQLException e) {
            System.err.println("[PatientDAO] getPatientById failed: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Patient> getAllPatients() {
        String sql = "SELECT * FROM patients ORDER BY last_name, first_name";
        List<Patient> list = new ArrayList<>();

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.err.println("[PatientDAO] getAllPatients failed: " + e.getMessage());
        }
        return list;
    }

    @Override
    public boolean updatePatient(Patient patient) {
        String sql = """
            UPDATE patients
               SET first_name   = ?,
                   last_name    = ?,
                   date_of_birth= ?,
                   gender       = ?,
                   phone_number = ?,
                   email        = ?,
                   is_active    = ?
             WHERE patient_id   = ?
            """;

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, patient.getFirstName());
            pstmt.setString(2, patient.getLastName());

            if (patient.getDateOfBirth() != null) {
                pstmt.setDate(3, Date.valueOf(patient.getDateOfBirth()));
            } else {
                pstmt.setNull(3, Types.DATE);
            }

            pstmt.setString(4, patient.getGender());
            pstmt.setString(5, patient.getPhoneNumber());
            pstmt.setString(6, patient.getEmail());
            pstmt.setBoolean(7, patient.isActive());
            pstmt.setInt(8, patient.getPatientId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[PatientDAO] updatePatient failed: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deletePatient(int patientId) {
        String sql = "DELETE FROM patients WHERE patient_id = ?";

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, patientId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                System.err.println("[PatientDAO] Cannot delete: patient has linked appointments.");
            } else {
                System.err.println("[PatientDAO] deletePatient failed: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<Patient> searchPatientByName(String namePart) {
        String sql = """
            SELECT * FROM patients
             WHERE LOWER(first_name) LIKE LOWER(?)
                OR LOWER(last_name)  LIKE LOWER(?)
             ORDER BY last_name, first_name
            """;
        List<Patient> results = new ArrayList<>();
        String pattern = "%" + namePart + "%";

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pattern);
            pstmt.setString(2, pattern);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) results.add(mapRow(rs));
            }

        } catch (SQLException e) {
            System.err.println("[PatientDAO] searchPatientByName failed: " + e.getMessage());
        }
        return results;
    }

    // -------------------------------------------------------
    // mapRow — maps one ResultSet row → Patient
    // Uses user's actual column names
    // -------------------------------------------------------
    private Patient mapRow(ResultSet rs) throws SQLException {
        Patient p = new Patient();
        p.setPatientId(rs.getInt("patient_id"));
        p.setFirstName(rs.getString("first_name"));
        p.setLastName(rs.getString("last_name"));

        Date dob = rs.getDate("date_of_birth");
        if (dob != null) p.setDateOfBirth(dob.toLocalDate());

        p.setGender(rs.getString("gender"));
        p.setPhoneNumber(rs.getString("phone_number"));   // ← user's column name
        p.setEmail(rs.getString("email"));
        p.setActive(rs.getBoolean("is_active"));           // ← user's column

        Timestamp ts = rs.getTimestamp("registered_on");   // ← user's column name
        if (ts != null) p.setRegisteredOn(ts.toLocalDateTime());

        return p;
    }
}
