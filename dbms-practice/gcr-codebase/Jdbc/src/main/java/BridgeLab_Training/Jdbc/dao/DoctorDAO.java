package BridgeLab_Training.Jdbc.dao;

import BridgeLab_Training.Jdbc.model.Doctor;
import java.util.List;

public interface DoctorDAO {

    int addDoctor(Doctor doctor);

    boolean updateDoctorSpecialty(int doctorId, int specialtyId);

    List<Doctor> getDoctorsBySpecialty(String specialtyName);

    boolean deactivateDoctor(int doctorId);

    boolean hasFutureAppointments(int doctorId);
}