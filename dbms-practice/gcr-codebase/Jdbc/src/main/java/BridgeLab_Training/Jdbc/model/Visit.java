package BridgeLab_Training.Jdbc.model;

import java.time.LocalDate;

public class Visit {

    private int visitId;
    private int appointmentId;
    private String diagnosis;
    private String notes;
    private LocalDate visitDate;

    public Visit() {}

    public Visit(int appointmentId, String diagnosis, String notes) {
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.notes = notes;
        this.visitDate = LocalDate.now();
    }

    public int getVisitId() { return visitId; }
    public void setVisitId(int visitId) { this.visitId = visitId; }

    public int getAppointmentId() { return appointmentId; }
    public String getDiagnosis() { return diagnosis; }
    public String getNotes() { return notes; }
}