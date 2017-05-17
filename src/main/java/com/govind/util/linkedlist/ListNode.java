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
            System.out.print(singleLinkedList.val + " ");
            singleLinkedList = singleLinkedList.next;
        }
        System.out.println();
    }

    public static ListNode toListNode(int[] array){
        ListNode head = null, current = null;
        for (int i = 0; i < array.length; i++) {
            ListNode currentListNode = new ListNode(array[i]);
            if (head == null){
                head = current = currentListNode;
            }else {
                current.next = currentListNode;
                current = currentListNode;
            }
        }
        return head;
    }
}