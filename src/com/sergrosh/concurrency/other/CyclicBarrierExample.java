package com.sergrosh.concurrency.other;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author sroshchupkin
 */
public class CyclicBarrierExample {
    public static void main(String[] args){
        int eatersCount=10;
        Thread[] eaters=new Thread[eatersCount];
        for(int i=0;i<eatersCount;i++){
            eaters[i]=new Thread(new Eater());
        }
        Eater.barrier=new CyclicBarrier(eatersCount,new Runnable() {
            public void run() {
                System.out.println("end of barier");
            }
        });
        for(int i=0;i<eatersCount;i++){
            eaters[i].start();
        }
    }
}
 class Stick {
    boolean taken = false;
    int stickOwnerID = -1;
    public synchronized boolean  take(int id) {
        if (!taken) {
            stickOwnerID = id;
            taken = true;
            return true;
        }
        return false;
    }
    public synchronized  void drop() {
        taken = false;
        stickOwnerID = -1;

    }
}
  class Pair {
    static ArrayList <Pair> jf=new ArrayList<Pair>();
    static public void writeAns(){
        for(Pair  i:jf){
            System.out.print("id="+i.x+" "+i.y+" ");
        }
        System.out.println();
    }
    public int x,y;
    Pair(int x,int y){
        this.x=x;
        this.y=y;
    }
    static void add(int id){
        for(Pair i:jf){
            if(id==i.x){
                i.y++;
            }
        }
    }
    static void add(int id,int soj){
        jf.add(new Pair(id,soj));
    }
}


 class Eater implements Runnable {
    static ArrayList<Stick> sticks = new ArrayList<>();
    static CyclicBarrier barrier;
    static  int number = 0;
    static int numberOfSleepThreads = 0;
    private int id;
    Stick leftStick, rightStick;
    Eater() {
        sticks.add(new Stick());
        id = number;
        number++;
    }
    private boolean ready = false;
    void stickInit() {
        if (ready) return;
        ready = true;
        leftStick = sticks.get(id);
        rightStick = sticks.get((id + 1) % number);
    }
    private boolean eat() {
        if (rightStick.stickOwnerID == id && leftStick.stickOwnerID == id)
            return true;
        return false;
    }

    private void dropAll() {
        if (rightStick.stickOwnerID == id)
            leftStick.drop();
        if (leftStick.stickOwnerID == id)
            rightStick.drop();
    }

    public void run() {
        stickInit();
        Pair.add(id,0);
        while (true) {
            leftStick.take(id);
            rightStick.take(id);

            if (eat()) {
                //System.out.println("Pojral_id=" + id);
                Pair.add(id);
                dropAll();
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            } else {
                //System.out.println("NePojral_id=" + id);

            }
            dropAll();
            Pair.writeAns();
        }
    }

}