package BridgeLab_Training.Jdbc.service;

import BridgeLab_Training.Jdbc.model.Prescription;

import java.util.List;

public interface VisitService {

    int completeVisit(int appointmentId, String diagnosis,
                      String notes, List<Prescription> prescriptions);

    List<String> getPatientHistory(int patientId);
}