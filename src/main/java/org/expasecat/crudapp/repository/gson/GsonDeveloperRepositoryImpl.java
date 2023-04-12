package org.expasecat.crudapp.repository.gson;

import org.expasecat.crudapp.model.Developer;
import org.expasecat.crudapp.model.Status;
import org.expasecat.crudapp.repository.DeveloperRepository;
import org.expasecat.crudapp.utils.IOFileUtils;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {
    private final IOFileUtils<Developer> IO_UTILS = new IOFileUtils<>();
    private final String PATH_DEVELOPERS_JSON = "/home/byreal/Загрузки/Telegram Desktop/CrudUpdate 2/CrudUpdate/src/main/resources/developers.json";

    private List<Developer> getAllDevelopersInternal() {
        return IO_UTILS.deserialization(PATH_DEVELOPERS_JSON, Developer.class);
    }

    private Integer generateId(List<Developer> developers) {
        Developer maxIdDeveloper = developers.stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        return Objects.nonNull(maxIdDeveloper) ? maxIdDeveloper.getId() + 1 : 1;
    }

    @Override
    public Developer create(Developer entity) {
        List<Developer> developers = getAllDevelopersInternal();
        entity.setId(generateId(developers));
        developers.add(entity);
        IO_UTILS.serialization(PATH_DEVELOPERS_JSON,developers);
        return entity;
    }

    @Override
    public Developer read(Integer id) {
        return getAllDevelopersInternal().stream().filter(d -> d.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public Developer update(Developer entity) {
        List<Developer> developers = getAllDevelopersInternal();
        int id = entity.getId();
        for (Developer developer : developers) {
            if(id == developer.getId()) {
                developer.setFirstName(entity.getFirstName());
                developer.setLastName(entity.getLastName());
                developer.setSkills(entity.getSkills());
                developer.setSpeciality(entity.getSpeciality());
                break;
            }
        }
        IO_UTILS.serialization(PATH_DEVELOPERS_JSON, developers);
        return entity;
    }

    @Override
    public void delete(Integer id) {
        List<Developer> developers = getAllDevelopersInternal();
        developers.forEach(d -> {
            if(d.getId().equals(id)){
                d.setStatus(Status.DELETE);
            }
        });
        IO_UTILS.serialization(PATH_DEVELOPERS_JSON,developers);
    }

    @Override
    public List<Developer> getAll() {
        return getAllDevelopersInternal();
    }
}
