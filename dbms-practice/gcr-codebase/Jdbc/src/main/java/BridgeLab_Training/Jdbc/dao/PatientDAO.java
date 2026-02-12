package BridgeLab_Training.Jdbc.dao;

import BridgeLab_Training.Jdbc.model.Patient;
import java.util.List;

public interface PatientDAO {

    boolean existsByPhoneOrEmail(String phone, String email);

    int registerPatient(Patient patient);

    Patient getPatientById(int patientId);

    boolean updatePatient(Patient patient);

    List<Patient> searchPatientsByName(String name);

    Patient getPatientByPhone(String phone);

    List<String> getVisitHistoryByPatientId(int patientId);
}