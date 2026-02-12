package BridgeLab_Training.Jdbc.service;

import BridgeLab_Training.Jdbc.dao.PatientDAO;
import BridgeLab_Training.Jdbc.dao.PatientDAOImpl;
import BridgeLab_Training.Jdbc.model.Patient;

import java.util.List;

public class PatientServiceImpl implements PatientService {

    private final PatientDAO dao = new PatientDAOImpl();

    @Override
    public int registerPatient(Patient patient) {
        if (dao.existsByPhoneOrEmail(patient.getPhone(), patient.getEmail())) {
            throw new RuntimeException("Patient already exists");
        }
        return dao.registerPatient(patient);
    }

    @Override
    public Patient getPatientById(int patientId) {
        Patient p = dao.getPatientById(patientId);
        if (p == null) throw new RuntimeException("Patient not found");
        return p;
    }

    @Override
    public boolean updatePatient(Patient patient) {
        return dao.updatePatient(patient);
    }

    @Override
    public List<Patient> searchPatientsByName(String name) {
        return dao.searchPatientsByName(name);
    }

    @Override
    public Patient getPatientByPhone(String phone) {
        Patient p = dao.getPatientByPhone(phone);
        if (p == null) throw new RuntimeException("Patient not found");
        return p;
    }

    @Override
    public List<String> getVisitHistory(int patientId) {
        return dao.getVisitHistoryByPatientId(patientId);
    }
}