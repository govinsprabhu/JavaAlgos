package com.govind.algo.linkedlist;

import com.govind.util.linkedlist.DoubleLinkedList;
import com.govind.util.linkedlist.ListNode;
import com.govind.util.linkedlist.MultiLinkedList;
import com.govind.util.linkedlist.SingleLinkedList;

import java.util.LinkedList;
import java.util.Stack;


/**
 * Created by govindp on 9/28/2015.
 */
public class Sample {


    public ListNode getSingleLinkedList() {
        ListNode listNode1 = new ListNode(10);
        ListNode listNode2 = new ListNode(60);
        ListNode listNode3 = new ListNode(30);
        ListNode listNode4 = new ListNode(50);
        ListNode listNode5 = new ListNode(40);
        ListNode listNode6 = new ListNode(20);
        ListNode listNode7 = new ListNode(7);
        ListNode listNode8 = new ListNode(8);
        ListNode listNode9 = new ListNode(9);
        ListNode listNode10 = new ListNode(10);
        ListNode listNode11 = new ListNode(11);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        return listNode1;
    }


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(10);
        ListNode listNode2 = new ListNode(60);
        ListNode listNode3 = new ListNode(30);
        ListNode listNode4 = new ListNode(50);
        ListNode listNode5 = new ListNode(40);
        ListNode listNode6 = new ListNode(20);
        ListNode listNode7 = new ListNode(7);
        ListNode listNode8 = new ListNode(8);
        ListNode listNode9 = new ListNode(9);
        ListNode listNode10 = new ListNode(10);
        ListNode listNode11 = new ListNode(11);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        //listNode4.next = listNode5;

        //listNode5.next = listNode6;
/*        listNode6.next = listNode7;
        listNode7.next = liNode8;
        listNode8.next = listNode9;
        listNode9.next = listNode10;
        listNode10.next = listNode11;
*/

        SingleLinkedList singleLinkedList = SingleLinkedList.getEvenLinkedList();
        //SingleLinkedList.print(new SampleCrawler().segregate(singleLinkedList));
        new Sample().swapKNodeFromFirtAndLast();
    }

    public void swapKNodeFromFirtAndLast(){
        int k = 3;
        ListNode listNode  = ListNode.toListNode(new int[]{1,2,3,4,5,6,7});
        ListNode head = listNode;
        int count = 0;
        while (listNode != null && count < k - 1){
            listNode = listNode.next;
            count++;
        }

        if (listNode == null){
            System.out.println("Not enough nodes");
            return;
        }


        ListNode fast = listNode;
        ListNode slow = head;
        count = 0;
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
            count ++;
        }
        int temp = listNode.val;
        listNode.val = slow.val;
        slow.val = temp;

        ListNode.print(head);
    }

    public void reverseKNodes(){
        SingleLinkedList singleLinkedList = SingleLinkedList.getEvenLinkedList();
        int k = 3;
        SingleLinkedList.print(reverseKNodes(singleLinkedList, k));
    }

    private SingleLinkedList reverseKNodes(SingleLinkedList singleLinkedList, int k) {
        SingleLinkedList first = null;
        SingleLinkedList second = singleLinkedList;
        SingleLinkedList third = null;
        int count = 0;
        while (second != null && count <= k - 1){
            third = second.getSingleLinkedList();
            second.setSingleLinkedList(first);
            first = second;
            second = third;
            count ++;
        }

        if (second != null){
            singleLinkedList.setSingleLinkedList(reverseKNodes(second, k));
        }

        return first;
    }

    public void mergeTwoLinkedList(){
        SingleLinkedList singleLinkedList = SingleLinkedList.getOddLinkedList();
        SingleLinkedList singleLinkedList1 = SingleLinkedList.getEvenLinkedList();

        SingleLinkedList.print(merge(singleLinkedList1, singleLinkedList));
    }

    private SingleLinkedList merge(SingleLinkedList singleLinkedList1, SingleLinkedList singleLinkedList) {
        if (singleLinkedList == null){
            return singleLinkedList1;
        }

        if (singleLinkedList1 == null){
            return singleLinkedList;
        }


        if (singleLinkedList.getData() < singleLinkedList1.getData()){
            singleLinkedList.setSingleLinkedList(merge(singleLinkedList1, singleLinkedList.getSingleLinkedList()));
            return singleLinkedList;
        }

        singleLinkedList1.setSingleLinkedList(merge(singleLinkedList1.getSingleLinkedList(), singleLinkedList));
        return singleLinkedList1;
    }

    public void reverseEveryEvenNodes() {
        ListNode listNode = ListNode.toListNode(new int[]{1, 2, 3, 4});
        ListNode.print(reverseEveryEvenNodes(listNode));
    }

    public ListNode reverseEveryEvenNodes(ListNode listNode) {
        ListNode head = listNode;
        ListNode even = null;
        ListNode previous = null;
        while (head != null && head.next != null) {
            if (previous == null) {
                even = previous = head.next;
            } else {
                previous.next = head.next;
                previous = previous.next;
            }
            head.next = head.next.next;

            head = head.next;
        }
        head = listNode;

        ListNode reverse = new ListNode(0);
        reverseListNodeRecursive(even, reverse);
        even = reverse.next;
        while (even != null && head != null && head != null) {
            ListNode temp = head.next;
            head.next = even;
            ListNode preEven = even.next;
            even.next = temp;
            even = preEven;
            head = temp;
        }

        return listNode;
    }

    public void reverse() {
        ListNode head = ListNode.toListNode(new int[]{1, 2, 3, 4});
        ListNode listNode = new ListNode(0);
        reverseList(head,listNode);
        ListNode.print(listNode.next);
    }

    static class SampleDu{
        public boolean isSample(){
            return false;
        }
    }


    private ListNode reverseListNodeRecursive(ListNode reverse, ListNode listNode) {
        if (reverse.next == null) {
            listNode.next = reverse;
            return reverse;
        }



        ListNode current = reverse;

        ListNode next = reverseListNodeRecursive(reverse.next, listNode);

        next.next = current;
/*
        if (current == head){
            current.next = null;
        }
*/
        return current;
    }

    public void flatten() {
        DoubleLinkedList doubleLinkedList = DoubleLinkedList.getDoubleLinkedListWithDownPointer();
        DoubleLinkedList.print(flatten(doubleLinkedList));
    }

    private DoubleLinkedList flatten(DoubleLinkedList doubleLinkedList) {
        if (doubleLinkedList == null || doubleLinkedList.getNext() == null) {
            return doubleLinkedList;
        }


        return merge(doubleLinkedList, flatten(doubleLinkedList.getNext()));
    }


    private DoubleLinkedList merge(DoubleLinkedList doubleLinkedList1, DoubleLinkedList doubleLinkedList2) {
        if (doubleLinkedList1 == null) {
            return doubleLinkedList2;
        }


        if (doubleLinkedList2 == null) {
            return doubleLinkedList1;
        }
        if (doubleLinkedList1.getData() == 30 || doubleLinkedList2.getData() == 30) {
            System.out.println();
        }

        DoubleLinkedList result = null;
        if (doubleLinkedList1.getData() < doubleLinkedList2.getData()) {
            result = doubleLinkedList1;
            result.setPrevious(merge(doubleLinkedList1.getPrevious(), doubleLinkedList2));
        } else {
            result = doubleLinkedList2;
            result.setPrevious(merge(doubleLinkedList1, doubleLinkedList2.getPrevious()));
        }

        return result;

    }


    public void isPanlindrome() {
        Stack<Integer> stack = new Stack<>();

        SingleLinkedList singleLinkedList = SingleLinkedList.getOddLinkedList();

        SingleLinkedList slow = singleLinkedList;
        SingleLinkedList fast = singleLinkedList;
        int count = 0;
        while (fast != null && fast.getSingleLinkedList() != null) {
            stack.push(slow.getData());
            slow = slow.getSingleLinkedList();
            fast = fast.getSingleLinkedList().getSingleLinkedList();
            count++;

        }
        count *= 2;
        if (fast != null) {
            slow = slow.getSingleLinkedList();
            count++;
        }
        System.out.println(isPalindrome(singleLinkedList, count).isPalindrome);
        /*while (!stack.isEmpty() && slow != null && stack.pop() == slow.getData()) {
            slow = slow.getSingleLinkedList();
        }
        if (stack.isEmpty() && slow == null) {
            System.out.println("palindrome");
        } else {
            System.out.println("not palindrome");
        }*/
    }


    public Result isPalindrome(SingleLinkedList singleLinkedList, int length) {
        if (length == 0) {
            return null;
        }
        if (length == 1) {
            return new Result(singleLinkedList.getSingleLinkedList(), true);
        }
        if (length == 2) {
            return new Result(singleLinkedList.getSingleLinkedList().getSingleLinkedList(), singleLinkedList.getData() == singleLinkedList.getSingleLinkedList().getData());
        }
        Result result = isPalindrome(singleLinkedList.getSingleLinkedList(), length - 2);
        if (result == null) {
            return result;
        } else if (result.isPalindrome) {
            return new Result(result.next.getSingleLinkedList(), result.next.getData() == singleLinkedList.getData());
        }
        return new Result(null, false);
    }

    public class Result {
        SingleLinkedList next;
        boolean isPalindrome;

        public Result(SingleLinkedList next, boolean isPalindrome) {
            this.next = next;
            this.isPalindrome = isPalindrome;
        }
    }

    public void flatenSortedMultiLinkedList() {
        MultiLinkedList multiLinkedList = new MultiLinkedList(new MultiLinkedList(10), new MultiLinkedList(7), 5);
        multiLinkedList.right.down = new MultiLinkedList(20);
        multiLinkedList.right.right = new MultiLinkedList(new MultiLinkedList(28), new MultiLinkedList(22), 19);
        multiLinkedList.right.right.down.down = new MultiLinkedList(50);
        multiLinkedList.right.right.right.down = new MultiLinkedList(35);
        multiLinkedList.right.right.right.down.down = new MultiLinkedList(40);
        MultiLinkedList.print(flattenSortedMultiLinkedList(multiLinkedList));
    }

    private MultiLinkedList flattenSortedMultiLinkedList(MultiLinkedList multiLinkedList) {
        if (multiLinkedList == null || multiLinkedList.right == null) {
            return multiLinkedList;
        }


        return merge(multiLinkedList, flattenSortedMultiLinkedList(multiLinkedList.right));
    }

    private MultiLinkedList merge(MultiLinkedList a, MultiLinkedList b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        MultiLinkedList result = null;
        if (a.data < b.data) {
            result = a;
            result.down = merge(a.down, b);
        } else {
            result = b;
            result.down = merge(a, b.down);
        }

        return result;
    }


    public SingleLinkedList segregate(SingleLinkedList head) {
        SingleLinkedList last = head;
        while (last.getSingleLinkedList() != null) {
            last = last.getSingleLinkedList();
        }

        SingleLinkedList crawl = head;
        SingleLinkedList oddFirst = last;
        SingleLinkedList previous = null;
        while (crawl != oddFirst.getSingleLinkedList()) {
            if ((crawl.getData() & 1) == 1) {
                last.setSingleLinkedList(crawl);
                if (previous != null) {
                    previous.setSingleLinkedList(crawl.getSingleLinkedList());
                } else {
                    head = crawl.getSingleLinkedList();
                    previous = head;
                }
                last = last.getSingleLinkedList();
                crawl = crawl.getSingleLinkedList();
                last.setSingleLinkedList(null);
            } else {
                previous = crawl;
                crawl = crawl.getSingleLinkedList();
            }

        }

        return head;
    }

    public void sort(ListNode listNode) {
        ListNode head = listNode;
        ListNode firstLinkedList = listNode;
        ListNode secondLinkedList = listNode.next;
        ListNode next = secondLinkedList;
        while (firstLinkedList.next != null && firstLinkedList.next.next != null && next != null && next.next != null) {
            next = firstLinkedList.next;
            firstLinkedList.next = next.next;
            firstLinkedList = firstLinkedList.next;
            next.next = firstLinkedList.next;
        }
    }

    public static ListNode reverseList(ListNode a) {
        ListNode head = new ListNode(0);
        reverseList(a, head);
        return head.next;
    }

    public static ListNode reverseList(ListNode a, ListNode head) {
        if (a.next == null) {
            head.next = a;
            return a;
        }
        ListNode next = reverseList(a.next, head);
        next.next = a;
        a.next = null;
        return a;
    }

    public static ListNode subtract(ListNode a) {
        ListNode fast = a;
        ListNode slow = a;
        ListNode start = a;
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode mid = slow.next;
        slow.next = null;
        ListNode first = reverse(mid);
        ListNode backFirst = first;
        while (first != null) {
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
        while (second != null) {
            third = second.next;
            second.next = first;
            first = second;
            second = third;
        }
        return first;
    }

    public static ListNode reverseKNode(ListNode listNode, int k) {

        if (listNode == null) {
            return null;
        }

        ListNode head = listNode;
        ListNode first = null;
        ListNode second = listNode;
        ListNode third = null;
        int count = 0;
        while (second != null && count < k) {
            third = second.next;
            second.next = first;
            first = second;
            second = third;
            count++;
        }
        head.next = reverseKNode(second, k);
        return first;
    }

    public static ListNode reorderList(ListNode a) {
        int length = 0;
        ListNode start = a;

        if (a == null || a.next == null || a.next.next == null) {
            return a;
        }

        ListNode slow = a;
        ListNode fast = a;
        while (fast != null && fast.next != null && fast.next.next != null) {
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
        while (second != null) {
            third = second.next;
            second.next = first;
            first = second;
            second = third;
        }
        ListNode firstNext;
        while (first != null && start != null) {
            startNext = start.next;
            start.next = first;
            first = first.next;
            start.next.next = startNext;
            start = startNext;
        }

        return a;
    }

    public static ListNode reverseBetween(ListNode a, int m, int n) {
        int i = 0;
        ListNode start = a;
        ListNode end = a;
        ListNode previous = null;
        while (i < m - 1) {
            previous = start;
            start = start.next;
            i++;
        }
        i = 0;
        while (i < n - 1) {
            end = end.next;
            i++;
        }

        ListNode remainingEnd = end.next == null ? null : end.next;
        ListNode remainingStart = previous == null ? null : previous;

        end.next = null;
        ListNode first = null;
        ListNode second = start;
        ListNode third = null;
        while (second != null) {
            third = second.next;
            second.next = first;
            first = second;
            second = third;
        }
        if (remainingStart != null) {
            remainingStart.next = first;
        } else {
            a = first;
        }

        start.next = remainingEnd;
        return a;
    }

    public static ListNode swapPairs(ListNode a) {
        ListNode head = new ListNode(0);
        head.next = a;
        ListNode current = head;
        while (current.next != null && current.next.next != null) {
            current.next = swap(current.next, current.next.next);
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
        int count = 0;
        ListNode head = a;
        while (head != null) {
            head = head.next;
            count++;
        }
        b = b % count;
        int remaining = count - b;
        ListNode current = a;
        int k = 0;
        while (current != null && k < remaining - 1) {
            k++;
            current = current.next;
        }
        ListNode next = current.next;
        ListNode start = next;
        current.next = null;
        while (next != null && next.next != null) {
            next = next.next;
        }
        if (next == null) {
            start = a;
        } else {
            next.next = a;
        }
        return start;
    }

    public static ListNode removeNthFromEnd(ListNode a, int b) {
        ListNode fast = a;
        ListNode slow = a;
        ListNode previous = null;
        int count = 0;
        while (count < b && fast != null) {
            fast = fast.next;
            count++;
        }

        if (count <= b && fast == null) {
            slow = slow.next;
            return slow;
        }

        while (fast != null) {
            fast = fast.next;
            previous = slow;
            slow = slow.next;
        }

        previous.next = slow.next;
        return a;
    }

    public static ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        ListNode result = null;
        ListNode head = null;
        while (a != null && b != null) {
            if (a.val > b.val) {
                if (result == null) {
                    result = b;
                    head = result;

                } else {
                    result.next = b;
                    result = result.next;
                }
                b = b.next;
            } else {
                if (result == null) {
                    result = a;
                    head = result;

                } else {
                    result.next = a;
                    result = result.next;
                }
                a = a.next;
            }
        }

        if (a == null) {
            result.next = b;
        }

        if (b == null) {
            result.next = a;
        }

        return head;
    }

    public static ListNode deleteDuplicates(ListNode a) {
        ListNode current = a;
        ListNode previous = null;
        ListNode head = null;
        while (current != null) {
            if (current.next != null) {
                if (current.val == current.next.val) {
                    int preValue = current.val;
                    while (preValue == current.val) {
                        current = current.next;
                    }
                }
            }

            if (previous == null) {
                previous = current;
                head = previous;
            } else {
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
