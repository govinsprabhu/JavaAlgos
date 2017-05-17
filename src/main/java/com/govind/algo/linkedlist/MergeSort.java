package com.govind.algo.linkedlist;

import com.govind.util.linkedlist.ListNode;

/**
 * Created by govindp on 12/12/2015.
 */
public class MergeSort {

    class Header {
        ListNode head;

        public Header(ListNode mid) {
            head = mid;
        }
    }

    public static void main(String[] args) {
        new MergeSort().mergeSort();
    }


    public void mergeSort() {
        ListNode listNode = ListNode.toListNode(new int[]{5, 2, 3, 4, 1});
        ListNode.print(mergeSort(listNode));
//        ListNode.print(merge(ListNode.toListNode(new int[]{1,4,6,}), ListNode.toListNode(new int[]{2,4,8})));
    }

    private ListNode mergeSort(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return listNode;
        }

        ListNode mid = splitAtMid(listNode);
        ListNode second = mid.next;
        mid.next = null;
        ListNode firstHalf = mergeSort(listNode);
        ListNode secondHalf = mergeSort(second);
        return merge(firstHalf, secondHalf);
    }

    private ListNode merge(ListNode head1, ListNode mid1) {

        ListNode start = head1;
        ListNode mid = mid1;
        if (start == null) {
            return mid;
        }
        if (mid == null) {
            return start;
        }


        ListNode result = null;
        ListNode head = null;
        while (start != null && mid != null) {
            if (start.val < mid.val) {
                if (result == null) {
                    result = start;
                    head = start;
                } else {
                    result.next = start;
                    result = result.next;
                }
                start = start.next;
            } else {
                if (result == null) {
                    result = mid;
                    head = mid;

                } else {
                    result.next = mid;
                    result = result.next;
                }

                mid = mid.next;
            }
        }

        if (start == null) {
            result.next = mid;
        }

        if (mid == null) {
            result.next = start;
        }
        return head;
    }

    private ListNode splitAtMid(ListNode listNode) {
        ListNode slow = listNode;
        ListNode fast = listNode.next;
        if (slow == null || slow.next == null){
            return slow;
        }
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }


}
