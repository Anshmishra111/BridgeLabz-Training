package BridgeLab_Training.Jdbc.service;

import BridgeLab_Training.Jdbc.model.Doctor;
import java.util.List;

public interface DoctorService {

    int addDoctor(Doctor doctor);

    boolean updateDoctorSpecialty(int doctorId, int specialtyId);

    List<Doctor> getDoctorsBySpecialty(String specialty);

    boolean deactivateDoctor(int doctorId);
}