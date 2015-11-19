package com.sergrosh.concurrency.producerconsumer;

/**
 * Created by sroshchupkin on 19/11/15.
 */
import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        String importantInfo[] = {
                "Witcher 3",
                "Fallout 4",
                "GTA 5",
                "Assassins Creed"
        };
        Random random = new Random();

        for (int i = 0;
             i < importantInfo.length;
             i++) {
            drop.put(importantInfo[i]);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        drop.put("DONE");
    }
}
