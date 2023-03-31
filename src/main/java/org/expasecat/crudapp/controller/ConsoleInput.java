package org.expasecat.crudapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //получаем id
    public int getConsoleInputId(int countOfDevelopers) {
        int id = 0;
        boolean isValidId = false;
        while (!isValidId) {
            try {
                id = Integer.parseInt(br.readLine());
                if (id <= 0 || id > countOfDevelopers) {
                    System.out.println("Неверно введен ID. Повторите ввод.");
                } else {
                    isValidId = true;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException numbEx) {
                System.out.println("Неверно введен ID. Повторите ввод.");
            }
        }
        return id;
    }

    public String getConsoleInputValue() {
        String answer = null;
        while (answer == null) {
            try  {
                answer = br.readLine();
            } catch (IOException e) {
                System.out.println("Что-то пошло не так");
            }
            if (answer == null) {
                throw new RuntimeException("Поле не может быть пустым.");
            }
        }
        return answer;
    }
}
