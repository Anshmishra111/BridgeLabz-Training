package BridgeLab_Training.Jdbc.service;

import BridgeLab_Training.Jdbc.model.Patient;
import java.util.List;

public interface PatientService {

    int registerPatient(Patient patient);

    Patient getPatientById(int patientId);

    boolean updatePatient(Patient patient);

    List<Patient> searchPatientsByName(String name);

    Patient getPatientByPhone(String phone);

    List<String> getVisitHistory(int patientId);
}