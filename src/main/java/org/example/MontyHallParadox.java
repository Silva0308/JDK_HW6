package org.example;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти-Холла
 и наглядно убедиться в верности парадокса (запустить игру в цикле на 1000 и вывести итоговый счет).
 */
public class MontyHallParadox {
    /**
     * Главный метод класса
     * Запусксает симуляцию игры для определенного количества тестов
     * и собирает статистику о количестве выигрышей и проигрышей при переключении и не переключении дверей.
     */
    public static void main(String[] args) {
        int num = 1000;
        Map<Integer, String> results = new HashMap<>();

        int winWOutSw = 0;
        int wiWSw = 0;

        Random random = new Random();

        for (int i = 1; i <= num; i++) {
            int prizeDoor = random.nextInt(3) + 1;
            int chosenDoor = random.nextInt(3) + 1;

            int openedDoor;
            do {
                openedDoor = random.nextInt(3) + 1;
            } while (openedDoor == prizeDoor || openedDoor == chosenDoor);

            int switchedDoor = 6 - chosenDoor - openedDoor;

            boolean winWithoutSwitching = chosenDoor == prizeDoor;
            boolean winWithSwitching = switchedDoor == prizeDoor;

            if (winWithoutSwitching) {
                winWOutSw++;
                results.put(i, "Победа (без смены выбора)");
            } else if (winWithSwitching) {
                wiWSw++;
                results.put(i, "Победа (со сменой выбора)");
            } else {
                results.put(i, "Неудача");
            }
        }

        System.out.println("Полученные результаты:");
        for (Map.Entry<Integer, String> entry : results.entrySet()) {
            System.out.println("Тест: " + entry.getKey() + " - " + entry.getValue());
        }

        System.out.println("Побед (без смены выбора) : " + winWOutSw);
        System.out.println("Побед (со сменой выбора): " + wiWSw);
        System.out.println("Неудач: " + (num - (winWOutSw + wiWSw)));
    }
}