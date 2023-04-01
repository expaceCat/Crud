package org.expasecat.crudapp.repository;

import org.expasecat.crudapp.model.Developer;

interface DeveloperRepository extends GenericRepository <Developer,Integer> {
    void editLastName(int id, String lastName);
    void editFirstName(int id, String lastName);
    void addSkills(int id, String ... skill);
    void editSpeciality(int id, String speciality);
}

