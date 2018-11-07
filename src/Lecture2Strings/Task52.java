package Lecture2Strings;

import java.util.Scanner;

public class Task52 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите подстроку строку: ");
        String s1 = scanner.nextLine();
        System.out.print("Введите строку: ");
        String s2 = scanner.nextLine();

        System.out.println(customContains(s1, s2));
    }

    private static boolean customContains(String substr, String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == substr.charAt(0)) {
                boolean contains = false;
                for (int j = 0; j < substr.length(); j++) {
                    if (i >= s.length() || s.charAt(i) != substr.charAt(j)) {
                        contains = false;
                        break;
                    };
                    i++;
                    contains = true;
                }

                if (contains) return true;
            }
        }

        return false;
    }
}
