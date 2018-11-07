package Lecture2Array;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class B_7_3 {
    public static void main(String[] args) {
        System.out.println("Введите размер вектора");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] vec = new int[n];

        Random random = new Random();
        for (int i = 0; i < n; i++)
            vec[i] = random.nextInt(100);

        System.out.println("Исходный вектор");
        System.out.println(Arrays.toString(vec));

        for (int i = 0; i < vec.length; i++) {
            if (isSimple(vec[i]))
                vec[i] = 0;
        }

        System.out.println("Финальный вектор");
        System.out.println(Arrays.toString(vec));
    }

    private static boolean isSimple(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }
}
