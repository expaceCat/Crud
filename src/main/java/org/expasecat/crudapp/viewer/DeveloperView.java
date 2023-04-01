package org.expasecat.crudapp.viewer;

import org.expasecat.crudapp.controller.DeveloperController;

public class DeveloperView {
    ConsoleInput consoleInput = new ConsoleInput();
    DeveloperController developerController = new DeveloperController();
    boolean isStart = true;


    public void start () {
        System.out.println("Программа запущена.");
        while(isStart) {
            System.out.println();
            String MENU = "Введите команду:\nc - для создания объекта\nr - для получения объекта по id\n" +
                    "u - для редактирования объекта\nd - для удаления объекта\nq - сохранить и выйти";
            System.out.println(MENU);
            makeChoice();
        }

    }

    public void makeChoice() {
        String choice = consoleInput.getConsoleInputValue();
        switch (choice) {
            case "c":
                createNewDeveloper();
                System.out.println("Developer успешно добавлен");
                break;
            case "r":
                System.out.print("Введите id: ");
                System.out.println(developerController.getDeveloperById((consoleInput.getConsoleInputId(developerController.developersCount()))));
                break;
            case "u":
                editMenu();
                break;
            case "d":
                System.out.print("Введите id который хотите удалить: ");
                developerController.delete(consoleInput.getConsoleInputId(developerController.developersCount()));
                System.out.println("Статус Developer изменен на DELETE");
                break;
            case "q":
                developerController.saveToFile();
                System.out.println("Файл успешно сохранен.");
                isStart = false;
                break;
            default:
                System.out.println("Неверная команда");
        }
    }



    private void editMenu () {
        System.out.println("Введите id объекта который надо отредактировать:");
        int id = consoleInput.getConsoleInputId(developerController.developersCount());
        System.out.println(developerController.getDeveloperById(id));

        do {
            System.out.println("\nВведите что вы хотите отредактировать: name | lastname | skill | speciality | back - для выхода в меню");
            switch (consoleInput.getConsoleInputValue()) {
                case "name":
                    System.out.print("Введите новое имя: ");
                    developerController.editDeveloperFirstName(id, consoleInput.getConsoleInputValue());
                    System.out.println("Имя успешно изменено.");
                    System.out.println(developerController.getDeveloperById(id));
                    break;
                case "lastname":
                    System.out.print("Введите новую фамилию: ");
                    developerController.editDeveloperLastName(id, consoleInput.getConsoleInputValue());
                    System.out.println("Фамилия успешно изменена.");
                    System.out.println(developerController.getDeveloperById(id));

                    break;
                case "skill":
                    System.out.print("Введите навыки через пробел: ");
                    developerController.setDeveloperSkills(id, consoleInput.getConsoleInputValue());
                    System.out.println(developerController.getDeveloperById(id));
                    break;
                case "speciality":
                    System.out.print("Введите специальность: ");
                    developerController.editDeveloperSpeciality(id, consoleInput.getConsoleInputValue());
                    System.out.println(developerController.getDeveloperById(id));
                    break;
                case "back":
                    id = 0;
                    break;
            }
        } while (!(id == 0));
    }



    public void createNewDeveloper () {
        String firstName;
        String lastName;
        String specialty;
        String[] skills;
        System.out.print("Введите имя: ");
        firstName = consoleInput.getConsoleInputValue();
        System.out.print("Введите фамилию: ");
        lastName = consoleInput.getConsoleInputValue();
        System.out.print("Введите специальность: ");
        specialty = consoleInput.getConsoleInputValue();
        System.out.print("Введите навыки через пробел: ");
        skills = consoleInput.getConsoleInputValue().split(" ");
        developerController.newDeveloper(firstName, lastName, specialty, skills);
    }
}