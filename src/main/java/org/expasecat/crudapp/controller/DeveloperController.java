package org.expasecat.crudapp.controller;

import org.expasecat.crudapp.model.Developer;
import org.expasecat.crudapp.model.Skill;
import org.expasecat.crudapp.model.Speciality;
import org.expasecat.crudapp.repository.gson.GsonDeveloperRepositoryImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class DeveloperController {
    private final GsonDeveloperRepositoryImpl DEVELOPER_REPOSITORY = new GsonDeveloperRepositoryImpl();

    public List<Developer> getDevelopersList() {
        return DEVELOPER_REPOSITORY.getAll();
    }

    public Developer getDeveloperById(Integer id) {
        return DEVELOPER_REPOSITORY.read(id);
    }

    public Developer createDeveloper(String name, String lastName, List<Skill> skills, Speciality speciality) {
        return DEVELOPER_REPOSITORY.create(new Developer(null, name, lastName, skills, speciality));
    }

    public void deleteDeveloper(Integer id) {
        DEVELOPER_REPOSITORY.delete(id);
    }


    public Integer getMaxId() {
        Developer developer = getDevelopersList().stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        return Objects.nonNull(developer) ? developer.getId() : 1;
    }

    public void editName(Integer id, String name) {
        Developer developer = getDeveloperById(id);
        developer.setFirstName(name);
        DEVELOPER_REPOSITORY.update(developer);
    }

    public void editLastName(Integer id, String lastName) {
        Developer developer = getDeveloperById(id);
        developer.setLastName(lastName);
        DEVELOPER_REPOSITORY.update(developer);
    }

    public void editDeveloperSkill(Integer id, List<Skill> skills) {
       Developer developer = DEVELOPER_REPOSITORY.read(id);
       developer.setSkills(skills);
       DEVELOPER_REPOSITORY.update(developer);
    }

    public void editDeveloperSpeciality(Integer id, Speciality speciality) {
        Developer developer = getDeveloperById(id);
        developer.setSpeciality(speciality);
        DEVELOPER_REPOSITORY.update(developer);
    }
}
