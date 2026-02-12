package BridgeLab_Training.Jdbc.service;

import BridgeLab_Training.Jdbc.model.Speciality;
import java.util.List;

public interface SpecialityService {

    int addSpecialty(String name);

    List<Speciality> listSpecialties();

    void updateSpecialty(int id, String name);

    void deleteSpecialty(int id);
}