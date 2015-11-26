package com.sergrosh.concurrency.collections.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sergey on 11/25/2015.
 */
public class Consumer implements Runnable {

    protected BlockingQueue queue = null;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("take: " + queue.take());
            Thread.sleep(2000);

            System.out.println("take: " + queue.take());
            System.out.println("take: " + queue.poll(5, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
