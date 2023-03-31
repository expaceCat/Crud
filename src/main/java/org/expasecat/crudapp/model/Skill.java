package org.expasecat.crudapp.model;

public class Skill {
    private final String skill;
    private Status status;
    {
        status = Status.ACTIVE;
    }

    public String getSkill(){
        return skill;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public Skill(String skill) {
        this.skill = skill;
    }
}

