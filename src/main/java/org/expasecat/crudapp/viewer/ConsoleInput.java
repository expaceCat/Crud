package org.expasecat.crudapp.viewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Метод для получения значения и фильтрации по id
    public int getConsoleInputId(int maxId) {
        int result = 0;
        boolean isValidId = false;
        while (!isValidId) {
            try {
                result = Integer.parseInt(br.readLine());
                if (result <= 0 || result > maxId) {
                    System.out.println("Неверно введен ID. Повторите ввод.");
                } else {
                    isValidId = true;
                }
            } catch (IOException e) {
                System.out.println("Неверно введен ID. Повторите ввод.");
            }
        }
        return result;
    }

    public String getConsoleInputValue() {
        String answer = null;
        while (answer == null) {
            try  {
                answer = br.readLine();
            } catch (IOException e) {
                System.out.println("Ошибка ввода.");
            }
            if (answer == null) {
                throw new NullPointerException("Поле не может быть пустым.");
            }
        }
        return answer;
    }
}
