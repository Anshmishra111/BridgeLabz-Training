package BridgeLab_Training.Jdbc.service;

import BridgeLab_Training.Jdbc.dao.SpecialityDAO;
import BridgeLab_Training.Jdbc.dao.SpecialityDAOImpl;
import BridgeLab_Training.Jdbc.model.Speciality;

import java.util.List;

public class SpecialityServiceImpl implements SpecialityService {

    private final SpecialityDAO dao = new SpecialityDAOImpl();

    @Override
    public int addSpecialty(String name) {
        return dao.addSpecialty(new Speciality(name));
    }

    @Override
    public List<Speciality> listSpecialties() {
        return dao.getAllSpecialties();
    }

    @Override
    public void updateSpecialty(int id, String name) {
        dao.updateSpecialty(id, name);
    }

    @Override
    public void deleteSpecialty(int id) {
        if (dao.isSpecialtyInUse(id)) {
            throw new RuntimeException("Cannot delete specialty: in use by doctors");
        }
        dao.deleteSpecialty(id);
    }
}