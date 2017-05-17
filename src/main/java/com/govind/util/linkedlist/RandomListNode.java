package com.govind.util.linkedlist;


public class RandomListNode {
    public int label;
    public RandomListNode next, random;

    public RandomListNode(int x) {
        this.label = x;

    }

    public static void print(RandomListNode singleLinkedList){
        while (singleLinkedList  != null){
            System.out.print(singleLinkedList.label + " " + singleLinkedList.random.label+" -> ");
            singleLinkedList = singleLinkedList.next;
        }
        System.out.println();
    }
}
