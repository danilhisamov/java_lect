package Lecture6;

import java.util.*;

public class Task1 {
    public static void main(String[] args) {
        Random random = new Random();
        int rangeStart = 5;
        int rangeEnd = 67;

        HashSet<Integer> hashSet = new HashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();

        Set[] sets = {hashSet, treeSet, linkedHashSet};
        for (int i = 0; i < 50; i++) {
            for (Set set : sets) {
                set.add(random.nextInt(rangeEnd - rangeStart + 1) + rangeStart);
            }
        }

        for (Set set : sets) {
            System.out.println(set.getClass().getName());
            System.out.println(set.toString());
        }

        HashSet<Integer> hashSet2 = new HashSet<>(Arrays.asList(35, 44, 24, 52, 34, 11, 45));

        System.out.println("TreeSet containsAll of hashSet2: " + treeSet.containsAll(hashSet2));

        treeSet.removeAll(hashSet2);

        System.out.println("Second " + hashSet2.getClass().getName());
        System.out.println(hashSet2.toString());

        System.out.println("TreeSet after remove hashSet2 elements");
        System.out.println(treeSet.toString());
    }
}
