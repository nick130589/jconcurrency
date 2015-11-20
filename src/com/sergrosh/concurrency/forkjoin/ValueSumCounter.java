package com.sergrosh.concurrency.forkjoin;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by sroshchupkin on 20/11/15.
 */
public class ValueSumCounter extends RecursiveTask<Long> {
    private final Node node;

    public ValueSumCounter(Node node) {
        this.node = node;
    }

    @Override
    protected Long compute() {
        long sum = node.getValue();
        List<ValueSumCounter> subTasks = new LinkedList<>();

        for(Node child : node.getChildren()) {
            ValueSumCounter task = new ValueSumCounter(child);
            task.fork();
            subTasks.add(task);
        }

        for(ValueSumCounter task : subTasks) {
            sum += task.join();
        }

        return sum;
    }

    public static void main(String[] args) {

//        new ForkJoinPool().invoke(new ValueSumCounter());
    }

}