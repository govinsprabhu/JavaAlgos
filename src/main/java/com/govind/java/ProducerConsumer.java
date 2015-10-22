package com.govind.java;

import java.util.ArrayList;

/**
 * Created by govindp on 8/23/2015.
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        ArrayList<Integer> ArrayList = new ArrayList<>(4);
        Thread producerThread = new Thread(new Producer(ArrayList, 4), "Producer");
        Thread consumerThread = new Thread(new Consumer(ArrayList, 4), "Consumer");
        producerThread.start();
        consumerThread.start();

    }

}

class Producer implements Runnable {
    ArrayList<Integer> ArrayList;
    int size;

    public Producer(ArrayList<Integer> ArrayList, int size) {
        this.ArrayList = ArrayList;
        this.size = size;
    }

    @Override
    public void run() {
        System.out.println("Producer java is running");
        for (int i = 0; i < 7; i++) {
            System.out.println("Producer " + i + " started");


            while (size == ArrayList.size()) {
                synchronized (ArrayList) {

                    try {
                        System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting size:" + size);
                        ArrayList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (ArrayList) {

                ArrayList.add(i);
                ArrayList.notifyAll();
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
        System.out.println("Consumer java is running");
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

