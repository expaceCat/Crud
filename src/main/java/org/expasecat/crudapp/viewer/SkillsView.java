package org.expasecat.crudapp.viewer;

import org.expasecat.crudapp.controller.SkillController;
import org.expasecat.crudapp.model.Skill;

import java.util.List;

public class SkillsView {
    private final SkillController CONTROLLER = new SkillController();

    public String viewAllSkills() {
        StringBuilder sb = new StringBuilder();
        String result;
        for(Skill skill : CONTROLLER.getSkillList()) {
            sb.append("\n").append(skill.toString());
        }
        result = sb.toString();
        return result;
    }

    public void createSkill(String skill) {
        CONTROLLER.createSkill(skill);
        System.out.println("Навык успешно создан");
    }

    public int getMaxId() {
        return CONTROLLER.getMaxId();

    }

    public void editSkillList(Integer id, String skillInfo) {
        CONTROLLER.updateSkill(id, skillInfo);
    }

    public List<Skill> getSkillsList() {
        return CONTROLLER.getSkillList();
    }

}
