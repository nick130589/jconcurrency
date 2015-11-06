package com.sergrosh.concurrency;

/**
 * Created by sroshchupkin on 18/09/15.
 */
class Incremenator extends Thread
{

    private volatile boolean mIsIncrement = true;
    private volatile boolean mFinish = false;

    public void changeAction()
    {
        mIsIncrement = !mIsIncrement;
    }
    public void finish()
    {
        mFinish = true;
    }

    @Override
    public void run()
    {
        do
        {
            if(!mFinish)
            {
                if(mIsIncrement)
                    IncremenatorDemo.mValue++;
                else
                    IncremenatorDemo.mValue--;
                System.out.print(IncremenatorDemo.mValue + " ");
            }
            else
                return;

            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
        while(true);
    }
}

public class IncremenatorDemo
{

    public static int mValue = 0;

    static Incremenator mInc;

    public static void main(String[] args)
    {
        mInc = new Incremenator();

        System.out.print("Значение = ");

        mInc.start();
        for(int i = 1; i <= 3; i++)
        {
            try{
                Thread.sleep(i*2*1000);
            }catch(InterruptedException e){}

            mInc.changeAction();
        }

        mInc.finish();
    }
}