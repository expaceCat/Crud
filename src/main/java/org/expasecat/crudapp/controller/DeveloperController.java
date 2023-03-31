package org.expasecat.crudapp.controller;

import org.expasecat.crudapp.dao.DevelopersDao;
import org.expasecat.crudapp.model.Developer;
import org.expasecat.crudapp.repository.GsonDeveloperRepositoryImpl;
import org.expasecat.crudapp.viewer.DeveloperView;

public class DeveloperController {
    private final GsonDeveloperRepositoryImpl repository = new GsonDeveloperRepositoryImpl();

    public void newDeveloper (String firstName, String lastName, String specialty, String ... skills) {
        Developer developer = repository.newDeveloper(firstName, lastName, specialty, skills);
        repository.create(developer);
    }

    public Developer getDeveloperById(Integer id) {
        return repository.read(id);
    }

    public int developersCount() {
        return repository.developersSize();
    }

    public void editFirstName(int id, String name) {
        repository.editFirstName(id, name);
    }

    public void editLastName(int id, String lastName) {
        repository.editLastName(id, lastName);
    }

    public void setSkills (int id, String ... skill) {
        repository.addSkills(id, skill);
    }

    public void editSpeciality(int id, String speciality) {
        repository.editSpeciality(id, speciality);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public void saveToFile() {
        repository.saveToFile();
    }
}
