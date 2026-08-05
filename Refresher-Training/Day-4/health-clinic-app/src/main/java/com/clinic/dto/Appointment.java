package com.clinic.dto;

import java.time.LocalDateTime;

/**
 * DTO — Floor 1 Data Box for the appointments table.
 *
 * Matches user's schema exactly:
 *   appointment_id, patient_id, doctor_id,
 *   appointment_date DATETIME,   ← single combined column (date + time together)
 *   status ENUM('Scheduled','Completed','Cancelled')
 *
 * NOTE: User's schema uses DATETIME (not separate DATE + TIME columns).
 *       So appointmentDate is LocalDateTime here.
 */
public class Appointment {

    private int           appointmentId;
    private int           patientId;
    private int           doctorId;
    private LocalDateTime appointmentDate; // single DATETIME column in DB
    private String        status;          // Scheduled | Completed | Cancelled

    // -------------------------------------------------------
    // Constructors
    // -------------------------------------------------------

    public Appointment() {}

    public Appointment(int patientId, int doctorId, LocalDateTime appointmentDate) {
        this.patientId       = patientId;
        this.doctorId        = doctorId;
        this.appointmentDate = appointmentDate;
        this.status          = "Scheduled";
    }

    // -------------------------------------------------------
    // Getters & Setters
    // -------------------------------------------------------

    public int getAppointmentId()                       { return appointmentId; }
    public void setAppointmentId(int appointmentId)     { this.appointmentId = appointmentId; }

    public int getPatientId()               { return patientId; }
    public void setPatientId(int id)        { this.patientId = id; }

    public int getDoctorId()                { return doctorId; }
    public void setDoctorId(int id)         { this.doctorId = id; }

    public LocalDateTime getAppointmentDate()               { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime dt)        { this.appointmentDate = dt; }

    public String getStatus()               { return status; }
    public void setStatus(String status)    { this.status = status; }

    @Override
    public String toString() {
        return String.format(
            "Appointment[ID=%-4d | Patient#%-4d | Doctor#%-4d | %s | Status: %s]",
            appointmentId, patientId, doctorId,
            (appointmentDate != null ? appointmentDate.toString() : "N/A"),
            status
        );
    }
}
