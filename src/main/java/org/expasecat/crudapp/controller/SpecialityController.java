package org.expasecat.crudapp.controller;

import org.expasecat.crudapp.model.Speciality;
import org.expasecat.crudapp.repository.gson.GsonSpecialityRepositoryImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SpecialityController {
    private final GsonSpecialityRepositoryImpl SPECIALITY_REPOSITORY = new GsonSpecialityRepositoryImpl();

    public Integer getMaxId() {
        Speciality speciality = getAllSpecialities().stream().max(Comparator.comparing(Speciality::getId)).orElse(null);
        return Objects.nonNull(speciality) ? speciality.getId() : 1;
    }

    public Speciality getSpecialityBiId(Integer id){
            return SPECIALITY_REPOSITORY.read(id);
    }

    public List<Speciality> getAllSpecialities() {
        return SPECIALITY_REPOSITORY.getAll();
    }

    public void specialityUpdate(Integer id, String specialityStr) {
        Speciality speciality = new Speciality(id, specialityStr);
        SPECIALITY_REPOSITORY.update(speciality);
    }

    public Speciality createSpeciality(String specialityIn){
        Speciality speciality = new Speciality();
        speciality.setSpeciality(specialityIn);
        SPECIALITY_REPOSITORY.create(speciality);
        return speciality;
    }
}
