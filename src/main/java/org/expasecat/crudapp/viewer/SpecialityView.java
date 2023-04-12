package org.expasecat.crudapp.viewer;

import org.expasecat.crudapp.controller.SpecialityController;
import org.expasecat.crudapp.model.Speciality;

import java.util.List;
import java.util.stream.Collectors;

public class SpecialityView {
    private final SpecialityController CONTROLLER = new SpecialityController();

    public Speciality getSpecialityById(Integer id) {
        return CONTROLLER.getSpecialityBiId(id);
    }

    public Integer getMaxId() {
        return CONTROLLER.getMaxId();
    }

    public String getAllSpeciality(){
        List<Speciality> allSpeciality = CONTROLLER.getAllSpecialities();
        return allSpeciality.stream().map(sp -> "id: " + sp.getId() + ". " + sp.getSpeciality()).collect(Collectors.joining("\n"));
    }

    public Speciality createSpeciality(String speciality) {
        return CONTROLLER.createSpeciality(speciality);
    }

    public void updateSpeciality(Integer id, String updateInfo){
        CONTROLLER.specialityUpdate(id, updateInfo);
    }
}
