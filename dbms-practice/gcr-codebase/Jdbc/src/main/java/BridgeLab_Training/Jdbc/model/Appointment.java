package BridgeLab_Training.Jdbc.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private int appointmentId;
    private int patientId;
    private int doctorId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String status;

    public Appointment() {}

    public Appointment(int patientId, int doctorId, LocalDate appointmentDate, LocalTime appointmentTime) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = "SCHEDULED";
    }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public int getPatientId() { return patientId; }
    public int getDoctorId() { return doctorId; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public LocalTime getAppointmentTime() { return appointmentTime; }

    public String getStatus() { return status; }
}