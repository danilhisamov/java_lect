package Lecture2Array;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class B_7_9 {
    public static void main(String[] args) {
        System.out.println("Введите размер массива");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        Random random = new Random();
        for (int i = 0; i < n; i++)
            a[i] = random.nextInt(10);

        System.out.println("Исходный массив");
        System.out.println(Arrays.toString(a));

        int bInd = 0;
        boolean zeroExists = false; // так как в b изначально будут 0, надо держать свой флаг

        for (int num : a) {
            if (num == 0 && !zeroExists || !arrayContains(b, num)) {
                if (num == 0) zeroExists = true;
                b[bInd] = num;
                bInd++;
            }
        }

        System.out.println("Финальный массив");
        System.out.println(Arrays.toString(Arrays.copyOfRange(b, 0, bInd)));
    }

    // Arrays.binarySearch работает только на отсортированных массивах,
    // если порядок элементов сохранять необязательно, то можно и его использовать
    private static boolean arrayContains(int[] a, int key) {
        for (int num : a) {
            if (num == key)
                return true;
        }

        return false;
    }
}
