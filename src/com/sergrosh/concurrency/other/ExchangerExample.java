package com.sergrosh.concurrency.other;

import java.util.concurrent.Exchanger;

/**
 * Created by Sergey on 11/28/2015.
 */
public class ExchangerExample {
    public static void main(String[] args){
        Exchanger exchanger = new Exchanger();

        ExchangerRunnable exchangerRunnable1 =
                new ExchangerRunnable(exchanger, "A");

        ExchangerRunnable exchangerRunnable2 =
                new ExchangerRunnable(exchanger, "B");

        Thread thread1=new Thread(exchangerRunnable1);
        Thread thread2=new Thread(exchangerRunnable2);
        thread1.start();
        thread2.start();
    }

}




class ExchangerRunnable implements Runnable{

    Exchanger exchanger = null;
    Object    object    = null;

    public ExchangerRunnable(Exchanger exchanger, Object object) {
        this.exchanger = exchanger;
        this.object = object;
    }

    public void run() {
        try {
            Object previous = this.object;

            this.object = this.exchanger.exchange(this.object);

            System.out.println(
                    Thread.currentThread().getName() +
                            " exchanged " + previous + " for " + this.object
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}