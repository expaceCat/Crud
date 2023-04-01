package org.expasecat.crudapp.repository;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.expasecat.crudapp.model.Developer;
import org.expasecat.crudapp.model.Skill;
import org.expasecat.crudapp.model.Speciality;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class IOFile implements FileIO<Developer> {
    private final String PATH_DEVELOPERS_JSON = "src/main/resources/developers.json";
    private final String PATH_SKILLS_JSON = "src/main/resources/skills.json";
    private final String PATH_SPECIALITY_JSON = "src/main/resources/speciality.json";
    private final Gson gson = new Gson();


    @Override
    public void serialization(List<Developer> developers) {
        List<List<Skill>> skills = new ArrayList<>();
        List<Speciality> specialityList = new ArrayList<>();
        List<Developer> developerList = new ArrayList<>(developers);
        for (Developer developer : developerList) {
            skills.add(developer.getSkills());
            specialityList.add(developer.getSpeciality());
            developer.setSkills(null);
            developer.setSpeciality(null);
        }
        saveToFile(PATH_DEVELOPERS_JSON, developerList, gson);
        saveToFile(PATH_SPECIALITY_JSON, specialityList, gson);
        saveToFile(PATH_SKILLS_JSON, skills, gson);
    }

    private void saveToFile(String path, List<?> list, Gson gson){
        try(Writer writer = new FileWriter(path)){
            gson.toJson(list, writer);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки в файл: " + e.getMessage());
        }
    }

    @Override
    public List<Developer> deserialization() {
        List<Developer> developers;
        List<List<Skill>> skills;
        List<Speciality> specialityList;

        developers = readJsonFileToList(PATH_DEVELOPERS_JSON,Developer.class);
        skills = readJsonFileToList(PATH_SKILLS_JSON, new TypeToken<List<Skill>>(){}.getType());
        specialityList = readJsonFileToList(PATH_SPECIALITY_JSON, Speciality.class);
        if(developers != null && skills != null) {
            for (int i = 0; i < developers.size(); i++) {
                Developer developer = developers.get(i);
                developer.setSkills(skills.get(i));
                assert specialityList != null;
                developers.get(i).setSpeciality(specialityList.get(i));
            }
        }
        return developers;
    }

    private <T> List<T> readJsonFileToList(String filePath, Type parametrType) {
        List<T> list = null;
      try(Reader reader = new FileReader(filePath)) {
          Type type = TypeToken.getParameterized(List.class, parametrType).getType();
          list = gson.fromJson(reader, type);
      } catch (IOException e) {
          System.out.println("Неверный файл.");
      } catch (NullPointerException n) {
          System.out.println("Файл пустой.");
      }
        return list;
    }
}
