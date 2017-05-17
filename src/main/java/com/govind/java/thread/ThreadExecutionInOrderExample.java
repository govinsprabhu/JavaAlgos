package com.govind.java.thread;

/**
 * Created by govindp on 8/24/2015.
 */
public class    ThreadExecutionInOrderExample {
    public static void main(String[] args) {
        Thread thread1 = getThread();
        Thread thread2 = getThread();
        Thread thread3 = getThread();


        try {
            thread1.start();
            thread1.join();//makes calling thread to wait until thread1 to die.

            thread2.start();
            thread2.join();

            thread3.start();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main is completed");


    }

    private static Thread getThread() {
        return new Thread(){
                @Override
                public void run() {
                    try {
                        System.out.println("Thread started "+Thread.currentThread().getName());
                        Thread.sleep(2000);
                        System.out.println("Thread is finished "+Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
    }
}
