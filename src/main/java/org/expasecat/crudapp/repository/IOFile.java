package org.expasecat.crudapp.repository;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.expasecat.crudapp.model.Developer;
import org.expasecat.crudapp.model.Skill;
import org.expasecat.crudapp.model.Speciality;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile implements FileIO<Developer> {
    private final String pathDevelopersJson = "src/main/resources/developers.json";
    private final String pathSkillsJson = "src/main/resources/skills.json";
    private final String pathSpecialityJson = "src/main/resources/speciality.json";
    private final Gson gson = new Gson();

    private void saveToFile(String path, List<?> list, Gson gson){
        try(Writer writer = new FileWriter(path)){
            gson.toJson(list, writer);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки в файл " + e.getMessage());
        }
    }



    public void serialization(List<Developer> developers) {
        List<List<Skill>> skills = new ArrayList<>();
        List<Speciality> specialityList = new ArrayList<>();
        List<Developer> devs = new ArrayList<>(developers);
        for (Developer developer : devs) {
            skills.add(developer.getSkills());
            specialityList.add(developer.getSpeciality());
            developer.setSkills(null);
            developer.setSpeciality(null);
        }
        saveToFile(pathDevelopersJson, devs, gson);
        saveToFile(pathSpecialityJson, specialityList, gson);
        saveToFile(pathSkillsJson, skills, gson);
        }

    @Override
    public List<Developer> deserialization() {
        List<Developer> developers = null;
        List<Skill> skills = null;
        List<Speciality> specialityList = null;

        try(Reader reader = new FileReader(pathSkillsJson)){
            skills = gson.fromJson(reader, new TypeToken<List<List<Skill>>>() {}.getType());
        } catch (IOException e) {
            System.out.println("Неверный файл Skills.");
        } catch (NullPointerException n) {
            System.out.println("Файл пустой.");
        }
        try(Reader reader = new FileReader(pathSpecialityJson)){
            specialityList = gson.fromJson(reader, new TypeToken<List<Speciality>>() {}.getType());
        } catch (IOException e) {
            System.out.println("Неверный файл Speciality.");
        } catch (NullPointerException n) {
            System.out.println("Файл пустой.");
        }
        try (Reader reader = new FileReader(pathDevelopersJson)) {
            developers = gson.fromJson(reader, new TypeToken<List<Developer>>() {}.getType());
        } catch (IOException e) {
            System.out.println("Неверный файл Developers.");
        } catch (NullPointerException n) {
            System.out.println("Файл пустой.");
        }

        if(developers != null && skills != null) {
            for (int i = 0; i < developers.size(); i++) {
                Developer developer = developers.get(i);
                developer.setSkills((List<Skill>) skills.get(i));
                assert specialityList != null;
                developers.get(i).setSpeciality(specialityList.get(i));
            }
        }
        return developers;
    }
}
