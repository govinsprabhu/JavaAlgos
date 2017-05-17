package com.govind.algo.platform.interviewbit.linkedlist;

import com.govind.util.linkedlist.ListNode;

/**
 * Created by 609600403 on 13/04/2016.
 */
public class Sample {
    public static void main(String[] args) {
        ListNode listNode1 = ListNode.toListNode(new int[]{1, 2, 3, 4, 5});
        ListNode.print(new Sample().swapPairs(listNode1));
    }

    public ListNode swapPairs(ListNode a) {

        ListNode head = null;
        ListNode previous = null;
        while (a != null && a.next != null){
            ListNode third = a.next.next;
            ListNode first = a;
            ListNode second = a.next;
            second.next = first;
            first.next = third;
            a = third;
            if (head == null){
                head = second;
                previous = first;
            }else {
                previous.next = second;
                previous = first;
            }
        }

        return head;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode previous = null;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            previous = slow;
            slow = slow.next;
        }

        if (fast != null){
            previous = slow;
            slow = slow.next;
        }

        previous.next = null;

        ListNode firstHalf = sortList(head);
        ListNode secondHalf = sortList(slow);
        return merge(firstHalf, secondHalf);
    }

    private ListNode merge(ListNode firstHalf, ListNode secondHalf) {
        ListNode result = new ListNode(0);
        ListNode head = result;
        while (firstHalf != null && secondHalf != null){
            if (firstHalf.val < secondHalf.val){
                result.next = firstHalf;
                firstHalf = firstHalf.next;
            }else {
                result.next = secondHalf;
                secondHalf = secondHalf.next;
            }

            result = result.next;
        }
        if (secondHalf != null){
            result.next = secondHalf;
        }

        if (firstHalf != null){
            result.next = firstHalf;
        }

        return head.next;
    }

    public ListNode insertionSortList(ListNode a) {
        if (a == null && a.next == null) {
            return a;
        }
        ListNode current = a.next;
        ListNode head = a;
        ListNode previousToCurrent = a;
        while (current != null) {
            ListNode key = current;
            current = current.next;
            key.next = null;
            ListNode headBack = head;
            previousToCurrent.next = current;
            ListNode previous = null;
            while (headBack != current) {
                if (key.val < headBack.val) {
                    break;
                }
                previous = headBack;
                headBack = headBack.next;
            }

            if (previous == null) {
                key.next = headBack;
                head = key;
            } else {
                previous.next = key;
                key.next = headBack;
            }
            if (headBack == current) {
                previousToCurrent = key;
            }

        }

        return head;
    }


    private void swap(ListNode previous, ListNode current) {
        int temp = previous.val;
        previous.val = current.val;
        current.val = temp;
    }


    public ListNode detectCycle(ListNode a) {
        ListNode slow = a;
        ListNode fast = a;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }

        ListNode current = a;
        while (current != slow) {
            current = current.next;
            slow = slow.next;
        }

        return current;
    }


    public ListNode addTwoNumbers(ListNode a, ListNode b) {
        return addTwoNumbers(a, b, 0);
    }

    public ListNode addTwoNumbers(ListNode a, ListNode b, int carry) {
        if (a == null && carry == 0) {
            return b;
        }

        if (b == null && carry == 0) {
            return a;
        }

        int aVal = a != null ? a.val : 0;
        int bVal = b != null ? b.val : 0;

        int sum = (aVal + bVal + carry);
        carry = (sum) / 10;
        sum = sum % 10;
        ListNode result = new ListNode(sum);

        result.next = addTwoNumbers(a == null ? null : a.next, b == null ? null : b.next, carry);
        return result;
    }


    public ListNode reorderList(ListNode a) {
        if (a == null || a.next == null || a.next.next == null) {
            return a;
        }

        ListNode fast = a;
        ListNode slow = a;
        ListNode previous = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            previous = slow;
            slow = slow.next;
        }

        if (fast != null) {
            previous = slow;
            slow = slow.next;
        }

        previous.next = null;
        ListNode reverse = reverse(slow);
        ListNode current = a;
        int count = 0;
        while (current != null) {
            if ((count & 1) == 0) {
                ListNode next = current.next;
                current.next = reverse;
                current = next;
            }
            if ((count & 1) == 1) {
                ListNode next = reverse.next;
                reverse.next = current;
                reverse = next;
            }
            count++;
        }
        return a;
    }

    public ListNode reverseBetween(ListNode a, int m, int n) {
        ListNode start = a;
        ListNode previous = null;
        while (m > 1) {
            previous = start;
            start = start.next;
            m--;
        }

        ListNode end = a;
        while (n > 1) {
            end = end.next;
            n--;
        }

        ListNode next = null;
        if (end.next != null) {
            next = end.next;
        }
        end.next = null;
        end = reverse(start);
        ListNode head = a;
        if (previous == null) {
            head = end;
            previous = end;
        } else {
            previous.next = end;
        }
        start.next = next;

        return head;
    }


    private ListNode reverse(ListNode start) {
        if (start == null || start.next == null) {
            return start;
        }

        ListNode first = null;
        ListNode second = null;
        ListNode third = start;
        while (third != null) {
            second = third;
            third = second.next;
            second.next = first;
            first = second;
        }

        return second;
    }


    public ListNode rotateRight(ListNode a, int b) {
        int length = 0;
        ListNode current = a;
        while (current != null) {
            current = current.next;
            length++;
        }

        b = b % length;
        if (b == 0) {
            return a;
        }

        current = a;
        while (b > 1) {
            current = current.next;
            b--;
        }

        ListNode previous = null;
        ListNode slow = a;
        while (current.next != null) {
            current = current.next;
            previous = slow;
            slow = slow.next;
        }
        if (previous == null) {
            return a;
        }

        previous.next = null;
        current.next = a;
        return slow;
    }

    public ListNode removeNthFromEnd(ListNode a, int b) {
        ListNode slow = a;
        ListNode fast = a;
        int count = b;
        while (fast != null && count > 1) {
            count--;
            fast = fast.next;
        }
        if ((fast == null) || (fast.next == null)) {
            a = a.next;
            return a;
        }

        ListNode previous = a;
        while (fast.next != null) {
            fast = fast.next;
            previous = slow;
            slow = slow.next;
        }

        previous.next = slow.next;
        return a;
    }

    public ListNode deleteDuplicates(ListNode a) {
        ListNode backUp = a;
        while (a != null) {
            if (a.next != null && a.val == a.next.val) {
                ListNode back = a;
                int dupli = a.val;
                while (a != null && a.val == dupli) {
                    a = a.next;
                }
                back.next = a;
            } else {
                a = a == null ? a : a.next;
            }
        }

        return backUp;
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode result = new ListNode(0);
        ListNode resultBack = result;
        while (a != null && b != null) {
            if (a.val < b.val) {
                result.next = a;
                a = a.next;
            } else {
                result.next = b;
                b = b.next;
            }
            result = result.next;
        }

        if (a != null) {
            result.next = a;
        }

        if (b != null) {
            result.next = b;
        }

        return resultBack.next;
    }

    public ListNode deleteDuplicates2(ListNode a) {
        ListNode previous = null;
        ListNode head = a;
        while (a != null && a.next != null) {
            if (a.val == a.next.val) {
                int dupli = a.val;
                while (a != null && a.val == dupli) {
                    a = a.next;
                }

                if (previous == null || previous.val == dupli) {
                    previous = a;
                    head = a;
                } else {
                    previous.next = a;
                }
            } else {
                previous = a;
                a = a.next;
            }
        }

        return head;
    }

    public ListNode reverse1(ListNode a) {
        if (a.next == null) {
            return a;
        }
        ListNode first = null;
        ListNode second = a;
        ListNode third = a;
        while (third != null) {
            second = third;
            third = second.next;
            second.next = first;
            first = second;
        }

        return first;
    }

    public int lPalin(ListNode A) {
        if (A.next == null) {
            return 1;
        }
        ListNode previous = null;
        ListNode slow = A;
        ListNode fast = A;
        while (fast != null && fast.next != null) {
            previous = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode slowBack = null;
        if (fast != null) {
            slowBack = slow;
            slow = slow.next;
        }


        ListNode reversed = reverse(slow);
        ListNode reverseBack = reversed;
        ListNode listNode = A;
        while (A != previous.next && reversed != null) {
            if (A.val != reversed.val) {
                return 0;
            }
            A = A.next;
            reversed = reversed.next;
        }

        if (A != previous.next || reversed != null) {
            return 0;
        }

        if (slowBack != null) {
            previous.next = slowBack;
            previous = previous.next;
        }
        previous.next = reverse(reverseBack);
        ListNode.print(listNode);
        return 1;
    }
}
