package Lecture3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Result {
    private Student student;
    private int minutes;

    private static Result[] results;

    public Result(Student student, int minutes) {
        this.student = student;
        this.minutes = minutes;
    }

    public static Result[] getSortedResults(SortDirection sd) {
        Result[] tmpArr = Arrays.copyOf(results, results.length);
        if (sd == SortDirection.ASC) {
            Arrays.sort(tmpArr, Comparator.comparingInt(o -> o.minutes));
        } else if (sd == SortDirection.DESC) {
            Arrays.sort(tmpArr, Comparator.comparingInt((Result o) -> o.minutes).reversed());
        }
        return tmpArr;
    }

    public static Student getWinnerStudent() {
        return getSortedResults(SortDirection.ASC)[0].student;
    }

    public static Student[] getTopResults(int count) {
        if (count > results.length)
            count = results.length;
        Result[] topResults = Arrays.copyOfRange(getSortedResults(SortDirection.ASC), 0, count);
        Student[] topStudents = new Student[topResults.length];
        for (int i = 0; i < topStudents.length; i++)
            topStudents[i] = topResults[i].student;

        return topStudents;
    }

    public static void printAggrInfo() {
        System.out.print("Participants count: ");
        System.out.println(results.length);
        System.out.print("Average time: ");
        Double avgTime = 0D;
        for (Result r : results)
            avgTime += r.minutes;
        System.out.println(avgTime / results.length);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return "Result{" +
                "student=" + student.getName() +
                ", minutes=" + minutes +
                '}';
    }

    public static void main(String[] args) {
        results = new Result[10];
        Random random = new Random();
        for (int i = 0; i < results.length; i++) {
            results[i] = new Result(new Student("Student #" + i), random.nextInt(10) + 1);
        }

        System.out.println("ASC order: ");
        System.out.println(Arrays.toString(getSortedResults(SortDirection.ASC)));
        System.out.println("DESC order: ");
        System.out.println(Arrays.toString(getSortedResults(SortDirection.DESC)));
        System.out.println("Winner: ");
        System.out.println(getWinnerStudent());

        int top = 3;
        System.out.println("Top " + top + ": ");
        System.out.println(Arrays.toString(getTopResults(top)));

        printAggrInfo();
    }
}

enum SortDirection {
    ASC,    //возрастание
    DESC    //убывание
}
