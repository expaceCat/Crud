package org.expasecat.crudapp.model;
import java.util.List;

public class Developer {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<Skill> skills;
    private Speciality speciality;
    private Status status = Status.ACTIVE;

    public Developer() {
    }

    public Developer(Integer id, String firstName, String lastName, List<Skill> skills, Speciality speciality) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.speciality = speciality;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Skill skill : skills) {
            sb.append(skill.getSkill()).append(" ");
        }
        String toStringSkills = sb.toString();

        return "----------\n id " + getId() + "\n" +
                "Имя: " + getFirstName() + "\n" +
                "Фамилия: " + getLastName() + "\n" +
                "Специальность: " + getSpeciality().getSpeciality() + "\n" +
                "Навыки: " + toStringSkills + "\n" +
                "Статус: " + getStatus();
    }


}
