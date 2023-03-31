package org.expasecat.crudapp.repository;

import java.util.List;

public interface FileIO<T> {
    void serialization(List<T> entity);
    List<T> deserialization();
}
