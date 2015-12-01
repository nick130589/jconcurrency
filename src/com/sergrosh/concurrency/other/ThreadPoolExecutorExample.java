package com.sergrosh.concurrency.other;

import com.sun.org.apache.xalan.internal.utils.FeatureManager;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Sergey on 11/29/2015.
 */
public class ThreadPoolExecutorExample {
    public static void main(String[] args){
        int  corePoolSize  =    5;
        int  maxPoolSize   =   10;
        long keepAliveTime = 5000;

        ExecutorService threadPoolExecutor =
                new ThreadPoolExecutor(
                        corePoolSize,
                        maxPoolSize,
                        keepAliveTime,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>()
                );

        Future future=threadPoolExecutor.submit(new CallableTask());
        try {
            System.out.printf(future.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(new Callable(){
            public Object call() throws Exception {
                System.out.println("Executed!");
                return "Called!";
            }
        },5,TimeUnit.SECONDS);


    }

}

class CallableTask implements Callable{

    @Override
    public Object call() throws Exception {
        return 5;
    }
}
