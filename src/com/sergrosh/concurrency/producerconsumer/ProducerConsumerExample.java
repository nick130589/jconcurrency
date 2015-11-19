package com.sergrosh.concurrency.producerconsumer;

/**
 * Created by sroshchupkin on 19/11/15.
 */
public class ProducerConsumerExample {
    public static void main(String[] args) {
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
}