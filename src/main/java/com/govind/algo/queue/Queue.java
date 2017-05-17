package com.govind.algo.queue;

/**
 * Created by govindp on 9/23/2015.
 */
public class Queue {
    int front;
    int rear;
    int[] array;

    public Queue(int size) {
        front = rear = -1;
        array = new int[size];
    }

    protected void enqueue(int number) {
        if (array.length - 1 <= rear) {
            System.out.println("Queue is full");
            return;
        }

        array[++rear] = number;
        if (front == -1) {
            front++;
        }
    }

    protected int dequeue() {
        if (front == -1) {
            System.out.println("Queue is empty");
            return -1;
        }

        int number = array[front];
        if (front == rear) {
            front = rear = -1;
        } else {
            front++;
        }
        System.out.println("dequeing " +number);
        return number;

    }

    protected void print(){
        for(int i = front; i <= rear; i++){
            System.out.print(array[i] +"  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue queue = new Queue(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.print();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();


    }
}
