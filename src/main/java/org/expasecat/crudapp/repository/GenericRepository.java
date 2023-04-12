package org.expasecat.crudapp.repository;

import java.util.List;

public interface GenericRepository<T,ID> {
    T create(T entity);
    T read(ID id);
    T update(T entity);
    void delete(ID id);
    List<T> getAll();
}
