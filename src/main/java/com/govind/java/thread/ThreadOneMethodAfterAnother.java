package com.govind.java.thread;

//import java.util.concurrent.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by govindp on 9/19/2015.
 */
public class ThreadOneMethodAfterAnother extends Thread {
    ReentrantLock sem1, sem2, sem3;

    public static void main(String[] args) {
        ThreadOneMethodAfterAnother threadOneMethodAfterAnother1 = new ThreadOneMethodAfterAnother();
        new Thread(threadOneMethodAfterAnother1, "2").start();
        new Thread(threadOneMethodAfterAnother1, "3").start();
        new Thread(threadOneMethodAfterAnother1, "1").start();
        System.out.println(Double.MIN_VALUE);
    }

    public ThreadOneMethodAfterAnother() {
        sem1 = new ReentrantLock();
        sem2 = new ReentrantLock();
        sem3 = new ReentrantLock();
        /*sem1.lock();
        sem2.lock();
        sem3.lock();*/

    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("1")) {
            first();
        }
        if (Thread.currentThread().getName().equals("2")) {
            two();
        }
        if (Thread.currentThread().getName().equals("3")) {
            third();
        }
    }

    public void first() {
        try {
            System.out.println(Thread.currentThread().getName() + " executing the first method");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " executed the first method");
            if (sem1.isHeldByCurrentThread())
                sem1.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void two() {
        try {
            if (!sem1.isHeldByCurrentThread())
                sem1.lock();
            System.out.println(Thread.currentThread().getName() + " executing the second method");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " executed the second method");
            if (sem2.isHeldByCurrentThread())
                sem2.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void third() {
        try {
            if (!sem2.isHeldByCurrentThread())
                sem2.lock();
            System.out.println(Thread.currentThread().getName() + " executing the third method");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " executed the third method");
            if (sem3.isHeldByCurrentThread())
                sem3.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
