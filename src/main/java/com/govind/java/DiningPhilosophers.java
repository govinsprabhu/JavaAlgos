package com.govind.java;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by govindp on 9/19/2015.
 */
public class DiningPhilosophers {
    public static void main(String[] args) {
        Philosopher[] philosopherses = new Philosopher[5];
        Chopsticks[] chopstickses = new Chopsticks[5];
        for (int i = 0; i < chopstickses.length; i++) {
            chopstickses[i] = new Chopsticks();
        }

        for (int i = 0; i < philosopherses.length; i++) {
            philosopherses[i] = new Philosopher(chopstickses[i], chopstickses[(i + 1) % chopstickses.length]);
            new Thread(philosopherses[i]).start();

        }

    }

}

class Chopsticks {
    Lock lock;

    public Chopsticks() {
        this.lock = new ReentrantLock();
    }

    public void getChopstick() {
        lock.lock();
    }

    public void releaseChopstick() {
        lock.unlock();
    }
}

class Philosopher extends Thread {
    Chopsticks left;
    Chopsticks right;

    public Philosopher(Chopsticks left, Chopsticks right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " created");
        eat();
    }

    public void eat() {
        getChopstick();
        eating();
        putChopstick();

    }

    private void putChopstick() {
        left.releaseChopstick();
        right.releaseChopstick();
        System.out.println(Thread.currentThread().getName() + " has released the chopStick");
    }

    private void eating() {
        try {
            System.out.println(Thread.currentThread().getName() + " has started eating");
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getChopstick() {
        left.getChopstick();
        right.getChopstick();
        System.out.println(Thread.currentThread().getName() + " has left and right chopStick");
        /*if(!left.getChopstick()){
            System.out.println(Thread.currentThread().getName() + " has not have left chopStick, returning");
            return false;
        }
        if(!right.getChopstick()) {
            System.out.println(Thread.currentThread().getName() + " has not have right chopStick, returning");
            left.releaseChopstick();
            return false;
        }
        System.out.println(Thread.currentThread().getName() + " has obtained the chopStick");
        return true;*/
    }
}
/*
class Philosopher extends Thread{
    Chopsticks left;
    Chopsticks right;

    public Philosopher(Chopsticks left, Chopsticks right){
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" created");
        eat();
    }

    public void eat(){
        if(getChopstick()){
            eating();
            putChopstick();
        }
    }

    private void putChopstick() {
        left.releaseChopstick();
        right.releaseChopstick();
        System.out.println(Thread.currentThread().getName() + " has released the chopStick");
    }

    private void eating() {
        try {
            System.out.println(Thread.currentThread().getName() + " has started eating");
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean getChopstick() {

        if(!left.getChopstick()){
            System.out.println(Thread.currentThread().getName() + " has not have left chopStick, returning");
            return false;
        }
        if(!right.getChopstick()) {
            System.out.println(Thread.currentThread().getName() + " has not have right chopStick, returning");
            left.releaseChopstick();
            return false;
        }
        System.out.println(Thread.currentThread().getName()+ " has obtained the chopStick");
        return true;
    }
}
*/
