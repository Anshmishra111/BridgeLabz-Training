package BridgeLab_Training.Jdbc.dao;

import BridgeLab_Training.Jdbc.config.DBConnection;
import BridgeLab_Training.Jdbc.model.Appointment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public boolean isSlotAvailable(int doctorId, LocalDate date) {
        String sql = """
            SELECT COUNT(*) FROM appointments
            WHERE doctor_id = ? AND appointment_date = ?
              AND status = 'SCHEDULED'
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ps.setDate(2, Date.valueOf(date));

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) < 10;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int bookAppointment(Appointment appt) {
        String sql = """
            INSERT INTO appointments
            (patient_id, doctor_id, appointment_date, appointment_time, status)
            VALUES (?, ?, ?, ?, 'SCHEDULED')
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, appt.getPatientId());
            ps.setInt(2, appt.getDoctorId());
            ps.setDate(3, Date.valueOf(appt.getAppointmentDate()));
            ps.setTime(4, Time.valueOf(appt.getAppointmentTime()));
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getDoctorAvailability(int doctorId, LocalDate date) {
        String sql = """
            SELECT appointment_time, COUNT(*) AS bookings
            FROM appointments
            WHERE doctor_id = ? AND appointment_date = ?
            GROUP BY appointment_time
        """;

        List<String> slots = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ps.setDate(2, Date.valueOf(date));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                slots.add(
                    rs.getTime("appointment_time") +
                    " -> " + rs.getInt("bookings")
                );
            }
            return slots;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cancelAppointment(int appointmentId) {
        String sql = "UPDATE appointments SET status='CANCELLED' WHERE appointment_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean rescheduleAppointment(int appointmentId, int doctorId,
                                         LocalDate date, String time) {
        String sql = """
            UPDATE appointments
            SET doctor_id=?, appointment_date=?, appointment_time=?
            WHERE appointment_id=?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ps.setDate(2, Date.valueOf(date));
            ps.setTime(3, Time.valueOf(time));
            ps.setInt(4, appointmentId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getDailySchedule(LocalDate date) {
        String sql = """
            SELECT p.name, d.name, a.appointment_time
            FROM appointments a
            JOIN patients p ON a.patient_id=p.patient_id
            JOIN doctors d ON a.doctor_id=d.doctor_id
            WHERE a.appointment_date=?
            ORDER BY a.appointment_time
        """;

        List<String> schedule = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                schedule.add(
                    rs.getTime(3) + " | " +
                    rs.getString(1) + " | " +
                    rs.getString(2)
                );
            }
            return schedule;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}