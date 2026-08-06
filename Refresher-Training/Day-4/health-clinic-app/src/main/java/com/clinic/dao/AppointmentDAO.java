package com.clinic.dao;

import com.clinic.dto.Appointment;

import java.sql.Connection;
import java.util.List;


public interface AppointmentDAO {

    /** @return generated appointment_id, or -1 on failure. */
    int insertAppointment(Appointment appointment);

    /** @return Appointment, or null if not found. */
    Appointment getAppointmentById(int appointmentId);

    /** @return all appointments, newest first. */
    List<Appointment> getAllAppointments();

    /** @return all Scheduled/Completed/Cancelled appointments for one patient. */
    List<Appointment> getAppointmentsByPatient(int patientId);

    /** @return all appointments for one doctor. */
    List<Appointment> getAppointmentsByDoctor(int doctorId);

    /**
     * Update status using an externally-provided connection (for transaction use).
     * The caller is responsible for commit/rollback on that connection.
     */
    boolean updateAppointmentStatus(int appointmentId, String status, Connection conn);

    /** Convenience method — cancel one appointment by ID (uses its own connection). */
    boolean cancelAppointment(int appointmentId);
}
