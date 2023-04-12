package org.expasecat.crudapp.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.expasecat.crudapp.repository.FileIO;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class IOFileUtils<T> implements FileIO {
    private final Gson GSON = new Gson();


    @Override
    public void serialization(String path, List<?> list) {
        saveToFile(path, list, GSON);
    }

    private void saveToFile(String path, List<?> list, Gson gson){
        try(Writer writer = new FileWriter(path)){
            gson.toJson(list, writer);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки в файл: " + e.getMessage());
        }
    }

    @Override
    public List<T> deserialization(String path, Type typeClass) {
        List<T> list;
        list = readJsonFileToList(path,typeClass);
        return list;
    }

    private List<T> readJsonFileToList(String filePath, Type parametrType) {
        List<T> list = null;
      try(Reader reader = new FileReader(filePath)) {
          Type type = TypeToken.getParameterized(List.class, parametrType).getType();
          list = GSON.fromJson(reader, type);
      } catch (IOException e) {
          System.out.println("Неверный файл.");
      } catch (NullPointerException n) {
          System.out.println("Файл пустой.");
      }
        return list;
    }
}
