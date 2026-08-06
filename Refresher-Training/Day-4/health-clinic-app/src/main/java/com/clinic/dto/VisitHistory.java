package com.clinic.dto;

import java.time.LocalDateTime;


public class VisitHistory {

    private int           visitId;
    private int           appointmentId;
    private String        diagnosis;
    private String        prescription;
    private String        visitNotes;     // column: visit_notes (TEXT)

    // -------------------------------------------------------
    // Constructors
    // -------------------------------------------------------

    public VisitHistory() {}

    public VisitHistory(int appointmentId, String diagnosis, String prescription) {
        this.appointmentId = appointmentId;
        this.diagnosis     = diagnosis;
        this.prescription  = prescription;
    }

    public VisitHistory(int appointmentId, String diagnosis,
                        String prescription, String visitNotes) {
        this(appointmentId, diagnosis, prescription);
        this.visitNotes = visitNotes;
    }

    // -------------------------------------------------------
    // Getters & Setters
    // -------------------------------------------------------

    public int getVisitId()                 { return visitId; }
    public void setVisitId(int visitId)     { this.visitId = visitId; }

    public int getAppointmentId()                   { return appointmentId; }
    public void setAppointmentId(int id)            { this.appointmentId = id; }

    public String getDiagnosis()                    { return diagnosis; }
    public void setDiagnosis(String diagnosis)      { this.diagnosis = diagnosis; }

    public String getPrescription()                     { return prescription; }
    public void setPrescription(String prescription)    { this.prescription = prescription; }

    public String getVisitNotes()                   { return visitNotes; }
    public void setVisitNotes(String visitNotes)    { this.visitNotes = visitNotes; }

    @Override
    public String toString() {
        return String.format(
            "Visit[ID=%-4d | Appt#%-4d | Diagnosis: %-25s | Prescription: %-20s | Notes: %s]",
            visitId, appointmentId,
            (diagnosis    != null ? diagnosis    : "—"),
            (prescription != null ? prescription : "—"),
            (visitNotes   != null ? visitNotes   : "—")
        );
    }
}
