package com.govind.algo.linkedlist;

import com.govind.util.linkedlist.ListNode;

import java.util.*;

/**
 * Created by govindp on 9/28/2015.
 */
public class Sample {


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(7);
        ListNode listNode8 = new ListNode(8);
        ListNode listNode9 = new ListNode(9);
        ListNode listNode10 = new ListNode(10);
        ListNode listNode11 = new ListNode(11);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        //listNode4.next = listNode5;

        //listNode5.next = listNode6;
/*        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = listNode9;
        listNode9.next = listNode10;
        listNode10.next = listNode11;
*/
        ListNode head = new ListNode(0);
        reverseList(listNode1,head);
        ListNode.print(head.next);
    }

    public static ListNode reverseList(ListNode a) {
        ListNode head = new ListNode(0);
        reverseList(a, head);
        return  head.next;
    }

    public static ListNode reverseList(ListNode a, ListNode head) {
        if (a. next == null){
            head.next = a;
            return a;
        }
         ListNode next = reverseList(a.next,head);
        next.next = a;
        a.next = null;
        return a;
    }

    public static ListNode subtract(ListNode a) {
        ListNode fast = a;
        ListNode slow = a;
        ListNode start = a;
        while (fast != null && fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode mid = slow.next;
        slow.next = null;
        ListNode first = reverse(mid);
        ListNode backFirst = first;
        while (first != null){
            start.val = first.val - start.val;
            start = start.next;
            first = first.next;
        }
        first = reverse(backFirst);

        slow.next = first;
        return a;
    }

    private static ListNode reverse(ListNode slow) {
        ListNode first = null;
        ListNode second = slow;
        ListNode third = null;
        while (second != null){
            third = second.next;
            second.next = first;
            first = second;
            second = third;
        }
        return first;
    }

    public static ListNode reverseKNode(ListNode listNode, int k){

        if (listNode == null){
            return null;
        }

        ListNode head = listNode;
        ListNode first = null;
        ListNode second = listNode;
        ListNode third = null;
        int count = 0;
        while (second != null && count < k){
            third = second.next;
            second.next = first;
            first = second;
            second = third;
            count ++;
        }
        head.next = reverseKNode(second,k);
        return first;
    }
    public static ListNode reorderList(ListNode a) {
        int length = 0;
        ListNode start = a;

        if(a == null || a.next == null || a.next.next == null){
            return a;
        }

        ListNode slow = a;
        ListNode fast = a;
        while (fast != null && fast.next != null && fast.next.next !=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode mid = slow;
        slow = slow.next;
        mid.next = null;
        ListNode first = null;
        ListNode second = slow;
        ListNode third = null;
        ListNode startNext;
        while (second != null){
            third = second.next;
            second.next = first;
            first = second;
            second = third;
        }
        ListNode firstNext;
        while (first != null && start != null){
            startNext = start.next;
            start.next = first;
            first = first.next;
            start.next.next = startNext;
            start = startNext;
        }

        return a;
    }
    public static ListNode reverseBetween(ListNode a, int m, int n) {
        int  i = 0;
        ListNode start = a;
        ListNode end = a;
        ListNode previous = null;
        while (i < m - 1){
            previous = start;
            start = start.next;
            i++;
        }
        i = 0;
        while (i < n - 1){
            end = end.next;
            i++;
        }

        ListNode remainingEnd = end.next == null? null:end.next;
        ListNode remainingStart  = previous == null? null :previous;

        end.next  = null;
        ListNode first = null;
        ListNode second = start;
        ListNode third = null;
        while (second != null){
            third = second.next;
            second.next = first;
            first = second;
            second = third;
        }
        if(remainingStart != null){
            remainingStart.next = first;
        }else {
            a = first;
        }

        start.next = remainingEnd;
        return a;
    }

    public static ListNode swapPairs(ListNode a) {
        ListNode head = new ListNode(0);
        head.next = a;
        ListNode current = head;
        while (current.next != null && current.next.next !=null){
            current.next = swap(current.next,current.next.next);
            current = current.next.next;
        }
        return head.next;
    }

    private static ListNode swap(ListNode next, ListNode next1) {
        next.next = next1.next;
        next1.next = next;
        return next1;
    }

    public static ListNode rotateRight(ListNode a, int b) {
        int count  = 0;
        ListNode head = a;
        while (head != null){
            head  = head.next;
            count++;
        }
        b = b % count;
        int remaining =  count - b;
        ListNode current = a;
        int k = 0;
        while (current != null && k < remaining - 1){
            k++;
            current = current.next;
        }
        ListNode next = current.next;
        ListNode start = next;
        current.next = null;
        while ( next != null && next.next != null){
            next = next.next;
        }
        if(next == null){
            start = a;
        }else {
            next.next = a;
        }
        return start;
    }

    public static ListNode removeNthFromEnd(ListNode a, int b) {
        ListNode fast  = a;
        ListNode slow = a;
        ListNode previous = null;
        int count = 0;
        while (count < b && fast != null){
            fast = fast.next;
            count++;
        }

        if(count <= b && fast == null){
            slow = slow.next;
            return slow;
        }

        while (fast != null){
            fast = fast.next;
            previous = slow;
            slow = slow.next;
        }

        previous.next = slow.next;
        return a;
    }

    public static ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null){
            return b;
        }
        if(b == null){
            return a;
        }
        ListNode result = null;
        ListNode head  = null;
        while (a != null && b != null){
            if (a.val > b.val){
                if (result == null){
                    result = b;
                    head = result;

                }else {
                    result.next = b;
                    result = result.next;
                }
                b = b.next;
            }else {
                if (result == null){
                    result = a;
                    head = result;

                }else {
                    result.next = a;
                    result = result.next;
                }
                a = a.next;
            }
        }

        if (a == null){
            result.next = b;
        }

        if (b == null){
            result.next = a;
        }

        return head;
    }

    public static ListNode deleteDuplicates(ListNode a) {
        ListNode current =a;
        ListNode previous = null;
        ListNode head = null;
        while (current != null){
            if (current.next != null){
                if(current.val == current.next.val ){
                    int preValue = current.val;
                    while (preValue == current.val){
                        current = current.next;
                    }
                }
            }

            if(previous == null){
                previous = current;
                head = previous;
            }else {
                previous.next = current;
                previous = previous.next;
            }
            current = current.next;
        }

        return head;
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMid(head);

        ListNode secondPart = mid.next;
        mid.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(secondPart);
        return merge(left, right);


    }



    private static ListNode merge(ListNode head, ListNode secondPart) {
        ListNode result = null;
        if (secondPart == null) {
            return head;
        }
        if (head == null) {
            return secondPart;
        }

        if (head.val < secondPart.val) {
            result = head;
            head = head.next;
            result.next = merge(head, secondPart);
        } else {
            result = secondPart;
            secondPart = secondPart.next;
            result.next = merge(head, secondPart);
        }
        return result;
    }

    private static ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = null;
        if (slow == null || slow.next == null) {
            return slow;
        }

        fast = head.next;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }

        return slow;
    }
}
