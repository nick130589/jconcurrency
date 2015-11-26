package com.sergrosh.concurrency.collections.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Sergey on 11/25/2015.
 */
public class Producer implements Runnable {

    protected BlockingQueue queue = null;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println("put 1");
            queue.put("1");

            Thread.sleep(3000);
            System.out.println("put 2");
            queue.put("2");

            Thread.sleep(3000);
            queue.put("3");
            System.out.println("put 3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
