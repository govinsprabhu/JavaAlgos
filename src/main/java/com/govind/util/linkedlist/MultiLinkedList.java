package com.govind.util.linkedlist;

/**
 * Created by govindp on 11/11/2015.
 */
public class MultiLinkedList {
    public MultiLinkedList right;
    public MultiLinkedList down;
    public int data;

    public MultiLinkedList(MultiLinkedList right, MultiLinkedList down) {
        this.right = right;
        this.down = down;
    }

    public MultiLinkedList(MultiLinkedList right, MultiLinkedList down, int data) {
        this.right = right;
        this.down = down;
        this.data = data;
    }

    public MultiLinkedList(int data) {
        this.data = data;
    }

    public static void print(MultiLinkedList multiLinkedList) {
        if (multiLinkedList == null)
            return;
        System.out.print(multiLinkedList.data + " -> ");
        print(multiLinkedList.down);
    }
}
