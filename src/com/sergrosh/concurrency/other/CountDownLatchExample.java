package com.sergrosh.concurrency.other;

import java.util.concurrent.CountDownLatch;

/**
 * @author sroshchupkin
 */
public class CountDownLatchExample {

    public static void main(String[] param){
        CountDownLatch latch = new CountDownLatch(3);

        Waiter      waiter      = new Waiter(latch);
        Decrementer decrementer = new Decrementer(latch);

        Thread thread1 = new Thread(waiter);
        Thread thread2 =new Thread(decrementer);
        thread1.start();
        thread2.start();
    }

}

class Waiter implements Runnable{

    CountDownLatch latch = null;

    public Waiter(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Waiter Released");
    }
}

class Decrementer implements Runnable {

    CountDownLatch latch = null;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {

        try {
            Thread.sleep(1000);
            this.latch.countDown();
            System.out.println("count down");

            Thread.sleep(1000);
            this.latch.countDown();
            System.out.println("count down");

            Thread.sleep(1000);
            this.latch.countDown();
            System.out.println("count down");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


