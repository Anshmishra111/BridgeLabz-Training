package BridgeLab_Training.Jdbc.dao;

import BridgeLab_Training.Jdbc.model.Speciality;
import java.util.List;

public interface SpecialityDAO {

    int addSpecialty(Speciality specialty);

    List<Speciality> getAllSpecialties();

    boolean updateSpecialty(int id, String newName);

    boolean isSpecialtyInUse(int specialtyId);

    boolean deleteSpecialty(int specialtyId);
}