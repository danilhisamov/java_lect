package Lecture2Array;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class B_7_5 {
    public static void main(String[] args) {
        System.out.println("Введите размер массива");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];

        Random random = new Random();
        for (int i = 0; i < n; i++)
            a[i] = random.nextInt(2);

        System.out.println("Исходный массив");
        System.out.println(Arrays.toString(a));

        int maxIndex = 0;
        int maxCount = 0;

        boolean newSequence = true;
        int currentIndex = 0;
        int currentCount = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                if (newSequence) {
                    currentIndex = i;
                    currentCount = 1;
                    newSequence = false;
                } else {
                    currentCount++;
                }

                if (i + 1 < a.length && a[i + 1] != 0) { //конец последовательности
                    if (currentCount > maxCount) {
                        maxIndex = currentIndex;
                        maxCount = currentCount;
                    }

                    newSequence = true;
                }
            }
        }

        System.out.println("Максимальное кол-во: " + maxCount);
        System.out.println("Индекс начала последовательности: " + maxIndex);
    }
}
