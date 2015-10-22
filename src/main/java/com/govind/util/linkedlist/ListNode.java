package com.govind.util.linkedlist;

/**
 * Created by govindp on 9/27/2015.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public static void print(ListNode singleLinkedList){
        while (singleLinkedList  != null){
            System.out.println(singleLinkedList.val);
            singleLinkedList = singleLinkedList.next;
        }
    }
}