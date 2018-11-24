package Lecture7;

import java.util.Random;
import java.util.Scanner;

public class Task28b {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int n = scanner.nextInt();
        System.out.print("Введите кол-во потоков: ");
        int k = scanner.nextInt();

        if (k > n) {
            System.out.println(String.format("Количество потоков[%d] больше размера массива [%d]", k, n));
            k = n;
            System.out.println(String.format("Количество потоков = %d", k));
        }
        int[] a = new int[n];

        int controlSum = fillArray(a);

        int threadPart = n / k;
        int lastThreadPart = n % k;

        if (lastThreadPart == 0) {
            lastThreadPart = threadPart;
        } else {
            lastThreadPart += threadPart;
        }

        MyThread[] threads = new MyThread[k];
        for (int i = 0; i < threads.length; i++) {
            if (i == threads.length - 1) {
                threads[i] = new MyThread(i * threadPart, lastThreadPart, a);
            } else {
                threads[i] = new MyThread(i * threadPart, threadPart, a);
            }
        }

        for (MyThread t : threads) t.start();

        for (MyThread t : threads) t.getThread().join();

        int sum = 0;
        for (MyThread t : threads) {
            sum += t.getTsum();
        }

        System.out.println("Control sum: " + controlSum);
        System.out.println("Result sum: " + sum);
    }

    private static int fillArray(int[] a) {
        Random random = new Random();
        int controlSum = 0;
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(1000);
            controlSum += a[i];
        }

        return controlSum;
    }
}

class MyThread implements Runnable {
    private Thread thread;
    private int beg;
    private int k; // кол-во элементов
    private int tSum = 0;
    private int[] a;

    public void run() {
        sum();
    }

    public MyThread(int beg, int k, int[] a) {
        thread = new Thread(this);
        this.beg = beg;
        this.k = k;
        this.a = a;
    }

    public void start() {
        thread.start();
        System.out.println("Thread[" + thread.getName() + "] started");
    }

    public void sum() {
        for (int i = beg; k > 0; k--) {
            tSum += a[i];
            i++;
        };
        System.out.println("Thread[" + thread.getName() + "] sum: " + tSum);
    }

    public Thread getThread() {
        return thread;
    }

    public int getTsum() {
        return tSum;
    }
}
