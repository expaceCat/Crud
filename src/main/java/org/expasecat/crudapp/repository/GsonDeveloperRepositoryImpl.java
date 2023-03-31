package org.expasecat.crudapp.repository;

import org.expasecat.crudapp.model.Developer;
import org.expasecat.crudapp.model.Skill;
import org.expasecat.crudapp.model.Speciality;
import org.expasecat.crudapp.model.Status;

import java.util.ArrayList;
import java.util.List;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {
    private final IOFile IOFILE = new IOFile();
    private List<Developer> developers = getDeveloperListFromFile();

    private int getIdForList() {
        if (developers != null) {
            return developers.size() + 1;
        } else {
            return 0;
        }
    }
//    {
//        skills.add(new Skill("Java"));
//        skills.add(new Skill("Spring"));
//        developers.add(new Developer(++ID,"Иван", "Киселев",skills , new Speciality("Разработчик")));
//        developers.add(new Developer(++ID,"Сергей", "Иванов", skills, new Speciality("Тестировщик")));
//        developers.add(new Developer(++ID,"Аргут", "Вартанян", skills, new Speciality("Уборщик")));
//    }


    private List<Developer> getDeveloperListFromFile() {
        return new ArrayList<>(IOFILE.deserialization());
    }



    public void saveToFile() {
        IOFILE.serialization(developers);
    }

    //создание пользователя
    @Override
    public void create(Developer developer) {
        developers.add(developer);
    }

    public Developer newDeveloper(String firstName, String lastName, String specialty, String ... skills) {
        List<Skill> listOfSkills = new ArrayList<>();
        for (String skill : skills) listOfSkills.add(new Skill(skill));
        return new Developer(getIdForList(), firstName, lastName, listOfSkills, new Speciality(specialty));
    }


    //перезапись записи.
    @Override
    public void update(Developer developer) {
        int position = 0;
        for (int i = 0; i < developers.size(); i++) {
            if (developers.get(i).getId() == developer.getId()) {
                position = i;
            }
        }
        developers.remove(position);
        developers.add(position, developer);
    }

    //возвращаем разработчика по id
    @Override
    public Developer read(Integer id) {
        return developers.stream().filter(developer -> developer.getId() == id).findAny().orElse(null);
    }

    //удаление (смена статуса на DELETE)
    @Override
    public void delete(Integer id) {
        Developer developer = read(id);
        developer.setStatus(Status.DELETE);
        developer.getSpeciality().setStatus(Status.DELETE);
        List<Skill> skills = developer.getSkills();
        for (Skill skill : skills) {
            skill.setStatus(Status.DELETE);
        }
        System.out.println("Удаление завершено.");
    }

    //Получаем размер массива нужен для консоли
    public int developersSize() {
        return developers.size();
    }

    //изменение имени
    public void editFirstName(int id, String name) {
        Developer developer = read(id);
        developer.setFirstName(name);
        update(developer);
    }
    //изменение фамилии
    @Override
    public void editLastName(int id, String lastName) {
        Developer developer = read(id);
        developer.setLastName(lastName);
        update(developer);
    }
    //добавление skill
    public void addSkills(int id, String ... skill) {
        Developer developer = read(id);
        ArrayList<Skill> result = new ArrayList<>();
        for (String value : skill) {
            result.add(new Skill(value));
        }
        developer.setSkills(result);
        update(developer);
        System.out.println("Skills успешно добавлены");
    }

    public void editSpeciality(int id, String speciality) {
        Developer developer = read(id);
        developer.getSpeciality().setSpeciality(speciality);
        System.out.println("Специальность успешно обновлена.");
    }

}
