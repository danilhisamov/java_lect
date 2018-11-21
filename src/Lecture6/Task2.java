package Lecture6;

import java.util.HashMap;
import java.util.Random;

public class Task2 {
    //TODO Предварительно построить оболочку для числа вхождений.
    // Не понял как и зачем эту оболочку тут использовать
    public static void main(String[] args) {
        Random random = new Random();
        int rangeStart = 5;
        int rangeEnd = 67;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < 100000; i++) {
            int num = random.nextInt(rangeEnd - rangeStart + 1) + rangeStart;
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        System.out.println(map.toString());
    }
}
