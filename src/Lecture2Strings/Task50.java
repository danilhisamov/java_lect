package Lecture2Strings;

import java.util.Scanner;

public class Task50 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите первую строку: ");
        String s1 = scanner.nextLine();
        System.out.print("Введите вторую строку: ");
        String s2 = scanner.nextLine();

        System.out.println(customEquals(s1, s2));
    }

    private static boolean customEquals(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) return false;
        }

        return true;
    }
}
