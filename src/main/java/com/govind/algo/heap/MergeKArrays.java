package com.govind.algo.heap;

import com.govind.util.arraylist.ArrayListUtils;
import com.govind.util.linkedlist.ListNode;

import java.util.ArrayList;

/**
 * Created by govindp on 10/24/2015.
 */
public class MergeKArrays {

    public static void main(String[] args) {
        ArrayList<ListNode> arrayList = new ArrayList<>();
        arrayList.add(ListNode.toListNode(new int[]{8, 20, 38, 44, 55, 65, 66, 79, 87}));
        arrayList.add(ListNode.toListNode(new int[]{68, 72}));
        arrayList.add(ListNode.toListNode(new int[]{5, 55, 61, 73, 89}));
        arrayList.add(ListNode.toListNode(new int[]{28, 73, 84, 96}));
        arrayList.add(ListNode.toListNode(new int[]{30, 73}));
        arrayList.add(ListNode.toListNode(new int[]{54, 82, 83}));
        arrayList.add(ListNode.toListNode(new int[]{15, 33, 38, 94, 100}));
        arrayList.add(ListNode.toListNode(new int[]{4}));
        arrayList.add(ListNode.toListNode(new int[]{22, 32, 42, 64, 86}));
        arrayList.add(ListNode.toListNode(new int[]{11, 78}));

        /*arrayList.add(ListNode.toListNode(new int[]{1,5,9}));
        arrayList.add(ListNode.toListNode(new int[]{2,4,7}));
        arrayList.add(ListNode.toListNode(new int[]{3,6,10}));*/

        ArrayList arrayList1 = new ArrayListUtils<Integer>().getArrayList(new Integer[]{4,2,1,5,3});
        System.out.println(new MergeKArrays().countInversions(arrayList1));
        ArrayListUtils.printArray(arrayList1);
    }

    public int countInversions(ArrayList<Integer> a) {
        return countInversions(a, 0, a.size() - 1);
    }

    private int countInversions(ArrayList<Integer> a, int l, int h) {
        if (l >= h) {
            return 0;
        }

        int inversion = 0;
        int mid = l + ((h - l) >> 1);
        inversion = countInversions(a, l, mid);
        inversion += countInversions(a, mid + 1, h);
        inversion += merge(a,l,mid,h);
        return inversion;
    }

    private int merge(ArrayList<Integer> a, int l, int mid, int h) {
        ArrayList<Integer> duplicate = new ArrayList<>();

        int i, j;
        int inversion = 0;
        for (i = l, j = mid + 1; i <= mid && j <= h; ) {
            if (a.get(i) <= a.get(j)) {
                duplicate.add(a.get(i));
                i++;
            } else {
                duplicate.add(a.get(j));
                inversion += mid -i + 1;
                j++;
            }
        }


        while (i <= mid) {
            duplicate.add(a.get(i));
            i++;
        }
        while (j <= h) {
            duplicate.add(a.get(j));
            j++;
        }

        for (i = l, j = 0; i <= h; i++, j++) {
            a.set(i, duplicate.get(j));
        }
        return inversion;
    }

    public ListNode merge(ArrayList<ListNode> arrayLists) {
        ListNode head = null, current = null;
        HeapSample heapSample = new HeapSample(arrayLists.size());

        ListNode listNode = arrayLists.get(0);

        for (int i = 0; i < arrayLists.size(); i++) {
            heapSample.setArrayAtIndex(i, arrayLists.get(i));
        }

        heapSample.minHeapify();

        for (; ; ) {
            ListNode sortObject = heapSample.getMin();
            if (sortObject.val == Integer.MAX_VALUE) {
                return head;
            }
            if (head == null) {
                head = current = sortObject;
            } else {
                current.next = sortObject;
                current = current.next;
            }
            if (sortObject.next != null)
                heapSample.replaceTop(sortObject.next);
            else
                heapSample.replaceTop(new ListNode(Integer.MAX_VALUE));
        }

    }
}

class HeapSample {
    private ListNode[] array;
    private int index;
    private static int MAX_CAPACITY;
    private ListNode min;

    public HeapSample(ListNode[] array) {
        this.array = array;
    }

    public void setArrayAtIndex(int index, ListNode data) {
        this.array[index] = data;
    }

    public HeapSample(int capacity) {
        MAX_CAPACITY = capacity;
        array = new ListNode[capacity];
        this.index = -1;
    }

    public void minHeapify() {
        for (int i = (MAX_CAPACITY - 1) / 2; i >= 0; i--) {
            minHeap(i);
        }
    }


    private void minHeap(int parent) {

        int left = 2 * parent + 1;
        int right = 2 * parent + 2;
        int max = parent;
        if (left < MAX_CAPACITY && array[left].val < array[parent].val) {
            max = left;
        }

        if (right < MAX_CAPACITY && array[right].val < array[max].val) {
            max = right;
        }
        if (parent != max) {
            swap(parent, max);
            minHeap(max);
        }
    }

    private void swap(int index, int parent) {
        ListNode sortObject = array[index];
        array[index] = array[parent];
        array[parent] = sortObject;
    }

    public ListNode getMin() {
        return array[0];
    }

    public void replaceTop(ListNode next) {
        array[0] = next;
        minHeap(0);
    }
}

