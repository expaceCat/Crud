package org.expasecat.crudapp.repository;

import java.util.List;

public interface GenericRepository<T,ID> {
    void create(T entity);
    T read(ID id);
    void update(T entity);
    void delete(ID id);



}
