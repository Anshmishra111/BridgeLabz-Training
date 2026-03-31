package BridgeLab_Training.Jdbc.dao;

import BridgeLab_Training.Jdbc.config.DBConnection;
import BridgeLab_Training.Jdbc.model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAOImpl implements DoctorDAO {

    @Override
    public int addDoctor(Doctor doctor) {
        String sql = """
            INSERT INTO doctors (name, contact, consultation_fee, specialty_id)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getContact());
            ps.setDouble(3, doctor.getConsultationFee());
            ps.setInt(4, doctor.getSpecialtyId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException("Error adding doctor", e);
        }
    }

    @Override
    public boolean updateDoctorSpecialty(int doctorId, int specialtyId) {
        String sql = "UPDATE doctors SET specialty_id = ? WHERE doctor_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, specialtyId);
            ps.setInt(2, doctorId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating specialty", e);
        }
    }

    @Override
    public List<Doctor> getDoctorsBySpecialty(String specialtyName) {
        String sql = """
            SELECT d.*
            FROM doctors d
            JOIN specialties s ON d.specialty_id = s.specialty_id
            WHERE s.specialty_name = ? AND d.is_active = TRUE
        """;

        List<Doctor> doctors = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, specialtyName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Doctor d = new Doctor();
                d.setDoctorId(rs.getInt("doctor_id"));
                d.setName(rs.getString("name"));
                d.setContact(rs.getString("contact"));
                d.setConsultationFee(rs.getDouble("consultation_fee"));
                d.setSpecialtyId(rs.getInt("specialty_id"));
                d.setActive(rs.getBoolean("is_active"));
                doctors.add(d);
            }
            return doctors;

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching doctors", e);
        }
    }

    @Override
    public boolean hasFutureAppointments(int doctorId) {
        String sql = """
            SELECT COUNT(*) FROM appointments
            WHERE doctor_id = ? AND appointment_date >= CURDATE()
              AND status = 'SCHEDULED'
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error checking appointments", e);
        }
    }

    @Override
    public boolean deactivateDoctor(int doctorId) {
        String sql = "UPDATE doctors SET is_active = FALSE WHERE doctor_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deactivating doctor", e);
        }
    }
}