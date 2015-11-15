package com.sergrosh.concurrency.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Sergey Roshchupkin on 11/15/2015.
 */
public class BankAccountWithLock {
        double balance;
        final int id;
        final Lock lock = new ReentrantLock();

    BankAccountWithLock(int id, double balance) {
            this.id = id;
            this.balance = balance;
        }

        void withdraw(double amount) {
            // Wait to simulate io like database access ...
            try {Thread.sleep(10l);} catch (InterruptedException e) {}
            balance -= amount;
        }

        void deposit(double amount) {
            // Wait to simulate io like database access ...
            try {Thread.sleep(10l);} catch (InterruptedException e) {}
            balance += amount;
        }

        static void transfer(BankAccountWithLock from, BankAccountWithLock to, double amount) {
            from.lock.lock();
            from.withdraw(amount);
            to.lock.lock();
            to.deposit(amount);
            to.lock.unlock();
            from.lock.unlock();
        }

        public static void main(String[] args) {
            final BankAccountWithLock fooAccount = new BankAccountWithLock(1, 100d);
            final BankAccountWithLock barAccount = new BankAccountWithLock(2, 100d);

            new Thread() {
                public void run() {
                    BankAccountWithLock.transfer(fooAccount, barAccount, 10d);
                }
            }.start();

            new Thread() {
                public void run() {
                    BankAccountWithLock.transfer(barAccount, fooAccount, 10d);
                }
            }.start();

        }
    }
