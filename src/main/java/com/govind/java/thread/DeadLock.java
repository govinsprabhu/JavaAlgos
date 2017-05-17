package com.govind.java.thread;

/**
 * Created by govindp on 10/18/2015.
 */
public class DeadLock {

    public DeadLock() {
    }

    public static void main(String[] args) {
        final String resource2 = "resource2";
        final String resource1 = "resource1";
        Thread thread1 = new Thread() {

            @Override
            public void run() {
                synchronized (resource1) {
                    System.out.println("resource 1 is locked by thread1");
                    try {
                        Thread.sleep(100);
                        System.out.println("trying to obtain resource 2 from thread 1");
                        synchronized (resource2){
                            System.out.println("resource 2 is locked by thread 1");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                synchronized (resource2){
                    System.out.println("resource 2  is locked by thread 2");
                    try {
                        Thread.sleep(100);
                        System.out.println("trying to obtain resource 1 from thread 2");
                        synchronized (resource1){
                            System.out.println("resource 1 is locked by thread 1");
                        }
                        notify();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread1.start();
        thread2.start();

    }

}
