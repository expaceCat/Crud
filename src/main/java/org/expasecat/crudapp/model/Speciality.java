package org.expasecat.crudapp.model;

public class Speciality {
    private Status status;
    private String speciality;
    public Speciality(String speciality) {
        status = Status.ACTIVE;
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
