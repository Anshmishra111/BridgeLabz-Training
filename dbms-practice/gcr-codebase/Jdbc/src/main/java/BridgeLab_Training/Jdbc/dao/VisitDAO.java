package BridgeLab_Training.Jdbc.dao;

import BridgeLab_Training.Jdbc.model.Prescription;
import BridgeLab_Training.Jdbc.model.Visit;

import java.util.List;

public interface VisitDAO {

    int recordVisit(Visit visit);

    void updateAppointmentStatus(int appointmentId, String status);

    List<String> getMedicalHistory(int patientId);

    void addPrescriptions(List<Prescription> prescriptions);
}