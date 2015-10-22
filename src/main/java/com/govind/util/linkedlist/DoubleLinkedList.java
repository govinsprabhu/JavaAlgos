package com.govind.util.linkedlist;

/**
 * Created by govindp on 9/20/2015.
 */
public class DoubleLinkedList {
    int data;
    DoubleLinkedList previous;
    DoubleLinkedList next;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public DoubleLinkedList getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleLinkedList previous) {
        this.previous = previous;
    }

    public DoubleLinkedList getNext() {
        return next;
    }

    public void setNext(DoubleLinkedList next) {
        this.next = next;
    }

    public static void print(DoubleLinkedList doubleLinkedList){
        while (doubleLinkedList.getNext() != null){
            System.out.print(doubleLinkedList.getData() + " ");
        }
        System.out.println();
    }
}
