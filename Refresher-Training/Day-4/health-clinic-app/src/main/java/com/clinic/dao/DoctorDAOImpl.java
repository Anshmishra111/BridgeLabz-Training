package com.clinic.dao;

import com.clinic.config.HikariConnectionPool;
import com.clinic.dto.Doctor;
import com.clinic.dto.Specialization;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DoctorDAOImpl — SQL for doctors table + doctor_specializations join.
 *
 * Column mapping (user's schema):
 *   doctor_id, first_name, last_name, phone_number, email, is_active
 */
public class DoctorDAOImpl implements DoctorDAO {

    @Override
    public int insertDoctor(Doctor doctor) {
        String sql = """
            INSERT INTO doctors (first_name, last_name, phone_number, email, is_active)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, doctor.getFirstName());
            pstmt.setString(2, doctor.getLastName());
            pstmt.setString(3, doctor.getPhoneNumber());
            pstmt.setString(4, doctor.getEmail());
            pstmt.setBoolean(5, doctor.isActive());
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("[DoctorDAO] insertDoctor failed: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public Doctor getDoctorById(int doctorId) {
        String sql = "SELECT * FROM doctors WHERE doctor_id = ?";

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, doctorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }

        } catch (SQLException e) {
            System.err.println("[DoctorDAO] getDoctorById failed: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        String sql = "SELECT * FROM doctors ORDER BY last_name, first_name";
        List<Doctor> list = new ArrayList<>();

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.err.println("[DoctorDAO] getAllDoctors failed: " + e.getMessage());
        }
        return list;
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        String sql = """
            UPDATE doctors
               SET first_name   = ?,
                   last_name    = ?,
                   phone_number = ?,
                   email        = ?,
                   is_active    = ?
             WHERE doctor_id    = ?
            """;

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, doctor.getFirstName());
            pstmt.setString(2, doctor.getLastName());
            pstmt.setString(3, doctor.getPhoneNumber());
            pstmt.setString(4, doctor.getEmail());
            pstmt.setBoolean(5, doctor.isActive());
            pstmt.setInt(6, doctor.getDoctorId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[DoctorDAO] updateDoctor failed: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteDoctor(int doctorId) {
        String sql = "DELETE FROM doctors WHERE doctor_id = ?";

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, doctorId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                System.err.println("[DoctorDAO] Cannot delete: doctor has linked appointments.");
            } else {
                System.err.println("[DoctorDAO] deleteDoctor failed: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean assignSpecialization(int doctorId, int specializationId) {
        String sql = "INSERT IGNORE INTO doctor_specializations (doctor_id, specialization_id) VALUES (?, ?)";

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, doctorId);
            pstmt.setInt(2, specializationId);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("[DoctorDAO] assignSpecialization failed: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean removeSpecialization(int doctorId, int specializationId) {
        String sql = "DELETE FROM doctor_specializations WHERE doctor_id = ? AND specialization_id = ?";

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, doctorId);
            pstmt.setInt(2, specializationId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[DoctorDAO] removeSpecialization failed: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Specialization> getDoctorSpecializations(int doctorId) {
        String sql = """
            SELECT s.*
              FROM specializations s
              JOIN doctor_specializations ds ON s.specialization_id = ds.specialization_id
             WHERE ds.doctor_id = ?
             ORDER BY s.name
            """;
        List<Specialization> specs = new ArrayList<>();

        try (Connection conn = HikariConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, doctorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Specialization s = new Specialization();
                    s.setSpecializationId(rs.getInt("specialization_id"));
                    s.setName(rs.getString("name"));
                    s.setDescription(rs.getString("description"));
                    specs.add(s);
                }
            }

        } catch (SQLException e) {
            System.err.println("[DoctorDAO] getDoctorSpecializations failed: " + e.getMessage());
        }
        return specs;
    }

    private Doctor mapRow(ResultSet rs) throws SQLException {
        Doctor d = new Doctor();
        d.setDoctorId(rs.getInt("doctor_id"));
        d.setFirstName(rs.getString("first_name"));
        d.setLastName(rs.getString("last_name"));
        d.setPhoneNumber(rs.getString("phone_number"));  // ← user's column name
        d.setEmail(rs.getString("email"));
        d.setActive(rs.getBoolean("is_active"));          // ← user's column
        return d;
    }
}
