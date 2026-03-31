package BridgeLab_Training.Jdbc.dao;

import BridgeLab_Training.Jdbc.config.DBConnection;
import BridgeLab_Training.Jdbc.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public boolean existsByPhoneOrEmail(String phone, String email) {
        String sql = "SELECT COUNT(*) FROM patients WHERE phone=? OR email=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phone);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Validation failed", e);
        }
    }

    @Override
    public int registerPatient(Patient p) {
        String sql = """
            INSERT INTO patients
            (name, dob, phone, email, address, blood_group)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getName());
            ps.setDate(2, Date.valueOf(p.getDob()));
            ps.setString(3, p.getPhone());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getAddress());
            ps.setString(6, p.getBloodGroup());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException("Error registering patient", e);
        }
    }

    @Override
    public Patient getPatientById(int id) {
        String sql = "SELECT * FROM patients WHERE patient_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Patient p = new Patient();
                p.setPatientId(rs.getInt("patient_id"));
                p.setName(rs.getString("name"));
                p.setDob(rs.getDate("dob").toLocalDate());
                p.setPhone(rs.getString("phone"));
                p.setEmail(rs.getString("email"));
                p.setAddress(rs.getString("address"));
                p.setBloodGroup(rs.getString("blood_group"));
                return p;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Patient not found", e);
        }
    }

    @Override
    public boolean updatePatient(Patient p) {
        String sql = """
            UPDATE patients
            SET name=?, address=?, blood_group=?
            WHERE patient_id=?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setString(2, p.getAddress());
            ps.setString(3, p.getBloodGroup());
            ps.setInt(4, p.getPatientId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Update failed", e);
        }
    }

    @Override
    public List<Patient> searchPatientsByName(String name) {
        String sql = "SELECT * FROM patients WHERE name LIKE ?";
        List<Patient> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Patient p = new Patient();
                p.setPatientId(rs.getInt("patient_id"));
                p.setName(rs.getString("name"));
                p.setPhone(rs.getString("phone"));
                p.setBloodGroup(rs.getString("blood_group"));
                list.add(p);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Search failed", e);
        }
    }

    @Override
    public Patient getPatientByPhone(String phone) {
        String sql = "SELECT * FROM patients WHERE phone=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Patient p = new Patient();
                p.setPatientId(rs.getInt("patient_id"));
                p.setName(rs.getString("name"));
                p.setDob(rs.getDate("dob").toLocalDate());
                p.setEmail(rs.getString("email"));
                p.setAddress(rs.getString("address"));
                p.setBloodGroup(rs.getString("blood_group"));
                return p;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Fetch failed", e);
        }
    }

    @Override
    public List<String> getVisitHistoryByPatientId(int patientId) {
        String sql = """
            SELECT a.appointment_date, d.name, v.diagnosis
            FROM visits v
            JOIN appointments a ON v.appointment_id = a.appointment_id
            JOIN doctors d ON a.doctor_id = d.doctor_id
            WHERE a.patient_id = ?
            ORDER BY a.appointment_date DESC
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
                    rs.getString(3)
                );
            }
            return history;

        } catch (SQLException e) {
            throw new RuntimeException("History fetch failed", e);
        }
    }
}