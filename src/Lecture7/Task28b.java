package Lecture7;

import java.util.ArrayList;
import java.util.Iterator;

public class Task28b {
    public static void main(String[] args) throws InterruptedException {
        int n = 10;
        int[] a = new int[n];
        int k = 5; // кол-во потоков

        int controlSum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = i;
            controlSum += a[i];
        }

        ArrayList<MyThread> list = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            MyThread t = new MyThread(0, a);
            list.add(t);
        }

        int c = n;
        Iterator<MyThread> it = list.iterator();
        while (c > 0) {
            while (it.hasNext()) {
                MyThread t = it.next();
                t.setK(t.getK() + 1);
                c--;
            }
            it = list.iterator();
        }

        it = list.iterator();
        int prev = 0;
        while (it.hasNext()) {
            MyThread t = it.next();
            t.setBeg(prev);
            t.setEnd(t.getBeg() + t.getK());
            prev = t.getEnd();
        }

        for (MyThread t : list) t.start();

        for (MyThread t : list) t.getThread().join();

        int sum = 0;
        for (MyThread t : list) {
            sum += t.getTsum();
        }

        System.out.println("Control sum: " + controlSum);
        System.out.println("Result sum: " + sum);
    }
}

class MyThread implements Runnable {
    private Thread thread;
    private int beg;
    private int end;
    private int k;
    private int tsum = 0;
    private int[] a;

    public void run() {
        sum();
    }

    public MyThread(int beg, int[] a) {
        thread = new Thread(this);
        this.beg = beg;
        this.a = a;
    }

    public void start() {
        thread.start();
        System.out.println("Thread[" + thread.getName() + "] started");
    }

    public void sum() {
        for (int i = beg; i < end; i++) tsum += a[i];
        System.out.println("Thread[" + thread.getName() + "] sum: " + tsum);
    }

    public int getBeg() {
        return beg;
    }

    public void setBeg(int beg) {
        this.beg = beg;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public Thread getThread() {
        return thread;
    }

    public int getTsum() {
        return tsum;
    }
}
