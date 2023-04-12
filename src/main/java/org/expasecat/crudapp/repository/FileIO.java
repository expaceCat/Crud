package org.expasecat.crudapp.repository;

import java.lang.reflect.Type;
import java.util.List;

public interface FileIO {
    void serialization(String path, List<?> entity);
    List<?> deserialization(String path, Type typeClass);
}
