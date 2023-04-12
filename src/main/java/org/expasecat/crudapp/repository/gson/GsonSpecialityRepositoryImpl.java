package org.expasecat.crudapp.repository.gson;

import org.expasecat.crudapp.model.Speciality;
import org.expasecat.crudapp.repository.SpecialtyRepository;
import org.expasecat.crudapp.utils.IOFileUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonSpecialityRepositoryImpl implements SpecialtyRepository {
    private final IOFileUtils<Speciality> IO_UTILS = new IOFileUtils<>();
    private final String PATH_SPECIALITY_JSON = "/home/byreal/Загрузки/Telegram Desktop/CrudUpdate 2/CrudUpdate/src/main/resources/speciality.json";

    private List<Speciality> getSpecialityInternal() {
        return IO_UTILS.deserialization(PATH_SPECIALITY_JSON, Speciality.class);
    }

    private Integer generateId(List<Speciality> specialities) {
        Speciality maxIdSpeciality = specialities.stream().max(Comparator.comparing(Speciality::getId)).orElse(null);
        return Objects.nonNull(maxIdSpeciality) ? maxIdSpeciality.getId() + 1 : 1;
    }
    @Override
    public Speciality create(Speciality entity) {
        List<Speciality> specialities = getSpecialityInternal();
        Integer id = generateId(specialities);
        entity.setId(id);
        specialities.add(entity);
        IO_UTILS.serialization(PATH_SPECIALITY_JSON, specialities);
        return entity;
    }

    @Override
    public Speciality read(Integer id) {
        return getSpecialityInternal().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Speciality update(Speciality entity) {
        List<Speciality> specialities = getSpecialityInternal();
        Speciality speciality = specialities.stream().filter(s -> s.getId().equals(entity.getId())).findFirst().orElse(null);
        if(speciality != null) {
            specialities.get(entity.getId()-1).setSpeciality(entity.getSpeciality());
            IO_UTILS.serialization(PATH_SPECIALITY_JSON, specialities);
        }
        return entity;
    }

    @Override
    public void delete(Integer id) {
        List<Speciality> specialities = getSpecialityInternal();
        specialities.removeIf(s -> s.getId().equals(id));
        IO_UTILS.serialization(PATH_SPECIALITY_JSON, specialities);
    }

    @Override
    public List<Speciality> getAll() {
        return getSpecialityInternal();
    }
}
