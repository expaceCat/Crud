package org.expasecat.crudapp.repository.gson;

import org.expasecat.crudapp.model.Skill;
import org.expasecat.crudapp.repository.SkillRepository;
import org.expasecat.crudapp.utils.IOFileUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class GsonSkillRepositoryImpl implements SkillRepository {
    private final IOFileUtils<Skill> IO_UTILS = new IOFileUtils<>();
    private final String PATH_SKILLS_JSON = "/home/byreal/Загрузки/Telegram Desktop/CrudUpdate 2/CrudUpdate/src/main/resources/skills.json";

    private List<Skill> getAllSkillsInternal() {
       return IO_UTILS.deserialization(PATH_SKILLS_JSON,Skill.class);
    }


    private Integer generateId(List<Skill> skills) {
        Skill maxIdSkill = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.nonNull(maxIdSkill) ? maxIdSkill.getId() + 1 : 1;
    }

    @Override
    public Skill create(Skill entity) {
        Skill newSkill;
        boolean isRepeat = false;
        List<Skill> skills = getAllSkillsInternal();
        for(Skill skill : skills) {
            if (skill.getSkill().equals(entity.getSkill())) {
                isRepeat = true;
                break;
            }
        }

        if(isRepeat) {
            try {
                throw new NullPointerException();
            }catch (NullPointerException e) {
                System.out.println("Такой навык уже существует.");
            }
        } else {
            Integer id = generateId(skills);
            entity.setId(id);
            skills.add(entity);
            IO_UTILS.serialization(PATH_SKILLS_JSON, skills);
        }
        return entity;
    }

    @Override
    public Skill read(Integer id) {
        return getAllSkillsInternal().stream().filter(s -> s.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public Skill update(Skill entity) {
        List<Skill> skills = getAllSkillsInternal();
        for(Skill skill : skills) {
            if(skill.getId().equals(entity.getId())) {
                skill.setSkill(entity.getSkill());
            }
        }
        IO_UTILS.serialization(PATH_SKILLS_JSON, skills);
        return entity;
    }

    @Override
    public void delete(Integer id) {
        List<Skill> skills = getAllSkillsInternal();
        skills.removeIf(s -> s.getId().equals(id));
        IO_UTILS.serialization(PATH_SKILLS_JSON,skills);
    }

    @Override
    public List<Skill> getAll() {
        return getAllSkillsInternal();
    }
}
