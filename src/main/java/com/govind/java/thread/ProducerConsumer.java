package com.govind.java.thread;

import java.util.ArrayList;

/**
 * Created by govindp on 8/23/2015.
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>(4);
        Thread producerThread1 = new Thread(new Producer(arrayList, 4), "Producer1");
        Thread producerThread2 = new Thread(new Producer(arrayList, 4), "Producer2");
        Thread consumerThread1 = new Thread(new Consumer(arrayList, 4), "Consumer1");
        Thread consumerThread2 = new Thread(new Consumer(arrayList, 4), "Consumer2");
        producerThread1.start();
        producerThread2.start();
        consumerThread1.start();
        consumerThread2.start();
        producerThread1.yield();
    }

}

class Producer implements Runnable {
    ArrayList<Integer> arraylist;
    int size;

    public Producer(ArrayList<Integer> ArrayList, int size) {
        this.arraylist = ArrayList;
        this.size = size;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +" is running");
        for (int i = 0; i < 7; i++) {
            System.out.println("Producer " + i + " started");
            while (size == arraylist.size()) {
                synchronized (arraylist) {
                    try {
                        System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting size:" + size);
                        arraylist.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (arraylist) {
                arraylist.add(i);
                arraylist.notifyAll();
            }
        }

    }
}

class Consumer implements Runnable {
    ArrayList<Integer> ArrayList;
    int size;

    public Consumer(ArrayList<Integer> ArrayList, int size) {
        this.ArrayList = ArrayList;
        this.size = size;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "  is running");
        while(true){
            while (ArrayList.isEmpty()) {
                synchronized (ArrayList) {
                    try {
                        System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting size:" + ArrayList.size());
                        ArrayList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (ArrayList) {
                System.out.println("Consumer is removing item :"+ArrayList.remove(0));
                ArrayList.notifyAll();
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

