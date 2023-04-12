package org.expasecat.crudapp.viewer;

import org.expasecat.crudapp.controller.DeveloperController;
import org.expasecat.crudapp.model.Developer;
import org.expasecat.crudapp.model.Skill;
import org.expasecat.crudapp.model.Speciality;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DeveloperView {
    private final ConsoleInput CONSOLE_INPUT = new ConsoleInput();
    private final SkillsView SKILLS_VIEW = new SkillsView();
    private final SpecialityView SPECIALITY_VIEW = new SpecialityView();
    private final DeveloperController CONTROLLER = new DeveloperController();


    public void createDeveloper() {
        Developer developer;
        String name;
        String lastName;
        List<Skill> skills;
        Speciality speciality;
        System.out.print("Введите имя: ");
        name = CONSOLE_INPUT.getConsoleInputValue();
        System.out.print("Введите фамилию: ");
        lastName = CONSOLE_INPUT.getConsoleInputValue();
        System.out.println("Выберите навыки из списка, введите ID через пробел: ");
        System.out.println(SKILLS_VIEW.viewAllSkills());
        skills = addSkills();
        System.out.println(SPECIALITY_VIEW.getAllSpeciality());
        System.out.println("Выберите навык из списка, введите его ID: ");
        int idSpeciality = CONSOLE_INPUT.getConsoleInputId(SPECIALITY_VIEW.getMaxId());
        speciality = SPECIALITY_VIEW.getSpecialityById(idSpeciality);
        developer = CONTROLLER.createDeveloper(name, lastName, skills, speciality);
        System.out.printf("Разработчик с ID: %d добавлен.\n", developer.getId());
    }

    public String viewDeveloperById(Integer id) {
        return CONTROLLER.getDeveloperById(id).toString();
    }

    public Integer getMaxId() {
        return CONTROLLER.getMaxId();
    }

    public void deleteDeveloper(Integer id) {
        CONTROLLER.deleteDeveloper(id);
    }

    public void editDeveloper(Integer id) {
        int choice;
        System.out.println(CONTROLLER.getDeveloperById(id));
        System.out.println("Введите что вы хотите редактировать:\n1 - ИМЯ,\n2 - ФАМИЛИЮ\n3 - НАВЫКИ\n4 - СПЕЦИАЛЬНОСТЬ");
        choice = CONSOLE_INPUT.getConsoleInputId(4);
        switch (choice) {
            case 1:
                System.out.print("Введите новое имя: ");
                String name = CONSOLE_INPUT.getConsoleInputValue();
                CONTROLLER.editName(id, name);
                System.out.println("Имя успешно изменено");
                break;
            case 2:
                System.out.print("Введите новую фамилию: ");
                String lastName = CONSOLE_INPUT.getConsoleInputValue();
                CONTROLLER.editLastName(id, lastName);
                System.out.println("Фамилия успешно изменена");
                break;
            case 3:
                System.out.println(SKILLS_VIEW.viewAllSkills());
                System.out.println("Выберите навыки из списка, введите ID через пробел: ");
                CONTROLLER.editDeveloperSkill(id, addSkills());
                System.out.println("Навыки успешно отредактированы.");
                break;
            case 4:
                Speciality speciality;
                System.out.println(SPECIALITY_VIEW.getAllSpeciality());
                System.out.println("Выберите навык из списка, введите его ID: ");
                int idSpeciality = CONSOLE_INPUT.getConsoleInputId(SPECIALITY_VIEW.getMaxId());
                speciality = SPECIALITY_VIEW.getSpecialityById(idSpeciality);
                CONTROLLER.editDeveloperSpeciality(id, speciality);
                System.out.println("Специальность успешно изменена.");
                break;
        }
    }

    private List<Skill> addSkills() {
        List<Skill> skills = new ArrayList<>();
        String[] ids;
        while (true) {
            try {
                ids = CONSOLE_INPUT.getConsoleInputValue().split(" ");
                if (ids.length > SKILLS_VIEW.getMaxId()) {
                    throw new NumberFormatException("Превышено количество ID Skills");
                }
                Arrays.stream(ids).forEach((n) -> {
                    if (!(n.matches("\\d+"))) {
                        throw new NumberFormatException();
                    }
                    if(Integer.parseInt(n) > SKILLS_VIEW.getMaxId()) {
                        throw new NumberFormatException();
                    }
                });
                for (String strID : ids) {
                    int id = Integer.parseInt(strID);
                    skills.add(SKILLS_VIEW.getSkillsList().get(id-1));
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверно введен ID. Повторите ввод цифрами.");
            }

        }
        return skills;
    }
}

