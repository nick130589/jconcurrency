package com.sergrosh.concurrency.other;

/**
 * @author sroshchupkin
 */

class MyThread implements Runnable {

    public int count = 0;

    private Thread t;

    public MyThread(Thread t) {

        this.t = t;

    }

    public void run() {
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        for (int i = 0; i < 1000000; i++) {

            count++;
        }

        System.out.println(count);

    }

}

class MyThreadjoin implements Runnable {

    public int count = 0;

    public void run() {

        for (int i = 0; i < 1000000; i++) {

            count++;
        }

        System.out.println(count);

    }

}

public class JoinExample {
    public static void main(String[] args) {

        Thread th = new Thread(new MyThreadjoin());

        Thread th1 = new Thread(new MyThread(th));

        Thread th2 = new Thread(new MyThread(th1));

        th.start();

        th1.start();

        th2.start();

    }
}
