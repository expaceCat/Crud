package org.expasecat.crudapp.controller;

import org.expasecat.crudapp.model.Skill;
import org.expasecat.crudapp.repository.gson.GsonSkillRepositoryImpl;

import java.util.List;

public class SkillController {
    private final GsonSkillRepositoryImpl SKILL_REPOSITORY = new GsonSkillRepositoryImpl();

    public List<Skill> getSkillList() {
        return SKILL_REPOSITORY.getAll();
    }

    public Skill getSkill(Integer id) {
        return SKILL_REPOSITORY.read(id);
    }

    public void createSkill(String skillInfo) {
        Skill skill = new Skill(null, skillInfo);
        SKILL_REPOSITORY.create(skill);
    }

    public void updateSkill(Integer id, String skillInfo) {
        Skill skill = getSkillList().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
        if(skill != null) {
            skill.setSkill(skillInfo);
            SKILL_REPOSITORY.update(skill);
        }
    }

    public int getMaxId() {
        return getSkillList().size();
    }

}
