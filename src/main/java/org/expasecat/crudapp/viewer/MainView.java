package org.expasecat.crudapp.viewer;

import org.expasecat.crudapp.model.Speciality;

public class MainView {
    private final ConsoleInput CONSOLE = new ConsoleInput();
    private final DeveloperView DEVELOPER_VIEW = new DeveloperView();
    private final SpecialityView SPECIALITY_VIEW = new SpecialityView();
    private final SkillsView SKILLS_VIEW = new SkillsView();

    public void start() {
        System.out.println("Программа запущена.");

        while (true) {
            String MENU = "\n\nc - СОЗДАТЬ ОБЪЕКТ РАЗРАБОТЧИК\nr - ПОЛУЧИТЬ ПО ID ДАННЫЕ О РАЗРАБОТЧИКЕ\n" +
                    "u - РЕДАКТИРОВАТЬ ДАННЫЕ РАЗРАБОТЧИКА\nd - УДАЛИТЬ ДАННЫЕ О РАЗРАБОТЧИКЕ\nq - ВЫХОД" +
                    "\nz - ДЛЯ РАБОТЫ С ДОПОЛНИТЕЛЬНЫМИ ДАННЫМИ\n\nВведите команду: ";
            System.out.print(MENU);
            String choice = CONSOLE.getConsoleInputValue();
            if(makeChoice(choice)){
                break;
            }
        }
    }

    private boolean makeChoice(String choice){
        boolean isExit = false;
        switch (choice) {
            case "c":
                DEVELOPER_VIEW.createDeveloper();
                break;
            case "r":
                System.out.printf("Всего в базе: %d @Разработчиков.\n", DEVELOPER_VIEW.getMaxId());
                System.out.printf("Введите ID от 1 до %d: ", DEVELOPER_VIEW.getMaxId());
                Integer id = CONSOLE.getConsoleInputId(DEVELOPER_VIEW.getMaxId());
                System.out.println(DEVELOPER_VIEW.viewDeveloperById(id));
                break;
            case "u":
                System.out.printf("Всего в базе: %d @Разработчиков.\n", DEVELOPER_VIEW.getMaxId());
                System.out.printf("Введите ID от 1 до %d для редактирования: ", DEVELOPER_VIEW.getMaxId());
                DEVELOPER_VIEW.editDeveloper(CONSOLE.getConsoleInputId(DEVELOPER_VIEW.getMaxId()));
                break;
            case "d":
                System.out.printf("Всего в базе: %d @Разработчиков.\n", DEVELOPER_VIEW.getMaxId());
                System.out.printf("Введите ID от 1 до %d для удаления: ", DEVELOPER_VIEW.getMaxId());
                System.out.print("Введите id разработчика которого хотите удалить: ");
                Integer id1 = CONSOLE.getConsoleInputId(DEVELOPER_VIEW.getMaxId());
                DEVELOPER_VIEW.deleteDeveloper(id1);
                System.out.println("Разработчик успешно удален.");
                break;
            case "z":
                System.out.println("us - РЕДАКТИРОВАТЬ СПИСОК НАВЫКОВ\nul - РЕДАКТИРОВАТЬ СПИСОК СПЕЦИАЛЬНОСТЕЙ");
                String inputChoice = CONSOLE.getConsoleInputValue();
                switch (inputChoice){
                    case "ul":
                        System.out.println(SPECIALITY_VIEW.getAllSpeciality());
                        System.out.printf("Всего в базе: %d @Профессии.\n", SPECIALITY_VIEW.getMaxId());
                        System.out.println("cs - ДОБАВИТЬ ПРОФЕССИЮ В СПИСОК\nuid - РЕДАКТИРОВАТЬ ПРОФЕССИЮ ПО ID");
                        String inputChoiceSpeciality = CONSOLE.getConsoleInputValue();
                        switch (inputChoiceSpeciality) {
                            case "cs":
                                System.out.print("Введите новое значение для специальности: ");
                                String specialityInputCreate = CONSOLE.getConsoleInputValue();
                                Speciality specialityNew = SPECIALITY_VIEW.createSpeciality(specialityInputCreate);
                                System.out.printf("Навык id: %d. %s успешно добавлен", specialityNew.getId(), specialityNew.getSpeciality());
                                break;
                            case "uid":
                                System.out.print("Введите ID для редактирования: ");
                                int idSpec = CONSOLE.getConsoleInputId(SPECIALITY_VIEW.getMaxId());
                                System.out.print("Введите новое значение для специальности: ");
                                String specialityUpdate = CONSOLE.getConsoleInputValue();
                                SPECIALITY_VIEW.updateSpeciality(idSpec,specialityUpdate);
                                break;
                        }
                        break;
                    case "us":
                        System.out.println(SKILLS_VIEW.viewAllSkills());
                        System.out.printf("Всего в базе: %d @Навыков.\n", SKILLS_VIEW.getMaxId());
                        System.out.println("cs - ДОБАВИТЬ НАВЫК В СПИСОК\nuid - РЕДАКТИРОВАТЬ НАВЫК ПО ID");
                        String inputChoiceSkill = CONSOLE.getConsoleInputValue();
                        switch (inputChoiceSkill) {
                            case "cs":
                                System.out.print("Введите новый навык: ");
                                String newSkill = CONSOLE.getConsoleInputValue();
                                SKILLS_VIEW.createSkill(newSkill);
                                break;
                            case "uid":
                                System.out.printf("Введите ID от 1 до %d для редактирования: ", SKILLS_VIEW.getMaxId());
                                int idEditSkill = CONSOLE.getConsoleInputId(SKILLS_VIEW.getMaxId());
                                System.out.print("Введите новое значение: ");
                                String newSkillEdited = CONSOLE.getConsoleInputValue();
                                SKILLS_VIEW.editSkillList(idEditSkill, newSkillEdited);
                                break;

                        }
                }
                break;
            case "q":
                isExit = true;
        }
        return isExit;
    }
}
