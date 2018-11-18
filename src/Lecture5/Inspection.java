package Lecture5;

public class Inspection {
    private static Citizen[] store;

    public static void main(String[] args) {
        store = new Citizen[]{
                new Citizen("Cit 1", 8, null),
                new Citizen("Cit 2", 12, null),
                new Citizen("Cit 3", 15, null),
                new Citizen("Cit 4", 17, null),
                new Citizen("Cit 5", 18, null),
                new Citizen("Cit 6", 25, null),
                new Citizen("Cit 7", 35, null), // lower tax
                new Citizen("Cit 8", 39, null),
                new Citizen("Cit 9", 45, null),
                new Citizen("Cit 10", 58, null),
        };

        store[6].setChildren(new Citizen[]{store[0], store[1], store[2]});
        store[9].setChildren(new Citizen[]{store[3], store[4], store[5]});

        for (Citizen citizen : store) {
            System.out.println("Citizen: " + citizen.getName() + ". Tax: " + calculateTax(citizen));
        }
    }

    private static float calculateTax(Citizen citizen) {
        if (citizen.getChildren() != null && citizen.getChildren().length >= 3) {
            int under18Count = 0;

            for (Citizen child : citizen.getChildren()) {
                if (child.getAge() < 18)
                    under18Count++;

                if (under18Count >= 3) {
                    return 0.5f;
                }
                ;
            }
        }

        return 1f;
    }
}

class Citizen {
    private String name;
    private int age;
    private Citizen[] children;

    public Citizen(String name, int age, Citizen[] children) {
        this.name = name;
        this.age = age;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Citizen[] getChildren() {
        return children;
    }

    public void setChildren(Citizen[] children) {
        this.children = children;
    }
}