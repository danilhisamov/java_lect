package Lecture2Strings;

import java.util.Arrays;

public class Task226 {
    public static void main(String[] args) {
        String s = "Lorem ipsum dolor sit amet consectetur adipiscing elit Aliquam eget ante eget diam posuere pellentesque quis non erat Maecenas dapibus";
        String[] arr = s.split(" ");

        System.out.println(Arrays.toString(arr));

        for(int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (compare(arr[j], arr[i]) == -1) switchElems(arr, i, j);
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    private static void switchElems(String[] arr, int ind1, int ind2) {
        String tmp = arr[ind1];
        arr[ind1] = arr[ind2];
        arr[ind2] = tmp;
    }

    //  return
    // -1   -- s1 < s2
    //  1   -- s1 > s2
    //  0   -- s1 = s2
    private static int compare(String s1, String s2) {
        if (s1 == null) return -1;
        if (s2 == null) return 1;
        for (int i = 0; i < s1.length(); i++) {
            try {
                if (s1.charAt(i) < s2.charAt(i)) return -1;
                if (s1.charAt(i) > s2.charAt(i)) return 1;
            } catch (IndexOutOfBoundsException e) {
                return 1; // строка s2 закончилась
            }
        }

        if (s1.length() < s2.length()) return -1;

        return 0;
    }
}
