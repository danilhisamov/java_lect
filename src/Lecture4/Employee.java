package Lecture4;

import java.util.Arrays;
import java.util.Comparator;

public class Employee implements Comparable<Employee> {
    private String name;
    private String rank;
    private int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee o) {
        return Integer.compare(salary, o.salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public static void main(String[] args) {
        Employee[] emps = {
                new Employee("John", 50_000),
                new Employee("Adele", 55_000),
                new Employee("Zed", 60_000),
                new Employee("Alice", 53_000)
        };

        System.out.println("Salary sort");
        Arrays.sort(emps);
        System.out.println(Arrays.toString(emps));

        System.out.println("Name sort");
        Arrays.sort(emps, new EmployeeNameComparator());
        System.out.println(Arrays.toString(emps));
    }
}

class EmployeeNameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
