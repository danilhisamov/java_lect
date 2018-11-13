package Lecture4;

import java.util.Random;

public class Polymorphism {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        int count = 20;
        Class[] classes = {A.class, B.class, C.class};
        I[] arr = new I[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            Class clazz = classes[random.nextInt(3)];
            I instance = (I) clazz.newInstance();
            arr[i] = instance;
        }

        for (I el : arr)
            System.out.println(el.toPortray());
    }
}

interface I {
    void setName(String name);
    String toPortray();
}

class C implements I {
    private String name;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toPortray() {
        return name + " " + getClass().getName();
    }
}

class B implements I {
    private String name;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toPortray() {
        return name + " " + getClass().getName();
    }
}

class A extends B { }
