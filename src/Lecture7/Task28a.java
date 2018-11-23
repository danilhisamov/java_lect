package Lecture7;

import java.util.Arrays;
import java.util.List;

public class Task28a {
    public static void main(String[] args) throws InterruptedException {
        int n = 100;
        int[] a = new int[n];

        int controlSum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = i;
            controlSum += a[i];
        }

        List<MyThread2> list = Arrays.asList(new MyThread2(a), new MyThread2(a));
        for (MyThread2 mt2 : list) {
            mt2.start();
            System.out.println("Thread[" + mt2.getName() + "] started");
        }

        for (MyThread2 mt2 : list) mt2.join();

        System.out.println("Control sum " + controlSum);
        for (MyThread2 mt2 : list)
            System.out.println("Thread[" + mt2.getName() + "] sum[" + mt2.getSum() + "]");
    }
}

class MyThread2 extends Thread {
    private int[] a;
    private int sum = 0;

    public MyThread2(int[] a) {
        this.a = a;
    }

    @Override
    public void run() {
        for (int anA : a) sum += anA;
        System.out.println("Thread run end. Thread[" + getName() + "] sum " + sum);
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
