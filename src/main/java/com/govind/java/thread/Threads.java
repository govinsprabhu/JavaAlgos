package com.govind.java.thread;

/**
 * Created by govindp on 12/2/2015.
 */
public class Threads {
    public static void main(String[] args) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The first run");
                ;
            }
        };

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Second run");
            }
        };


//        thread1.run();
/*
        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
        System.out.println("Main joined");
    }

    public void deadLockProduction(){

    }

    private class DeadLock implements Runnable{
        DeadLock deadLock1;
        DeadLock deadLock2;
        @Override
        public void run() {
            if (Thread.currentThread().getName().equals("1")){

                synchronized (deadLock1){
                    System.out.println("deadlock1 is locked ");
                    synchronized (deadLock2){
                        System.out.println("deadlock2 is locked");
                    }
                }
            }else {
                synchronized (deadLock2){
                    System.out.println("deadlock2 is locked");
                    synchronized (deadLock1){
                        System.out.println("deadlock1 is locked");
                    }
                }
            }


        }
    }

}
