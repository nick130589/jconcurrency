package com.sergrosh.concurrency.other;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;

/**
 * @author sroshchupkin
 */
public class PipedWriterReaderExample {
    public static void main(String[] args) {
        PipedWriter p = new PipedWriter();
        new Thread(new Client(p)).start();
        while (true) {
            Random r = new Random();
            try {
                p.write(r.nextInt());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Client implements Runnable {
    private PipedReader in;

    Client(PipedWriter s) {
        try {

            in = new PipedReader(s);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public void run() {
        while (true) {
            try {
                System.out.println(in.read());
            } catch (IOException e) {
//e.printStackTrace();
            }
        }
    }
}