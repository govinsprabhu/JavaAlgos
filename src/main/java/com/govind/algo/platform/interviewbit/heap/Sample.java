package com.govind.algo.platform.interviewbit.heap;

import com.govind.util.linkedlist.ListNode;

import java.util.*;

/**
 * Created by 609600403 on 22/04/2016.
 */
public class Sample {
    public static void main(String[] args) {
        //System.out.println(new TestClass().dNums(new ArrayListUtils<Integer>().getArrayList(new Integer[]{1,1,1,1}), 3));
        ListNode listNode1 = ListNode.toListNode(new int[]{1,11,14,15,100,250});
        ListNode listNode2 = ListNode.toListNode(new int[]{2,4,6});
        ListNode listNode3 = ListNode.toListNode(new int[]{0,12,17, 19});
        ListNode listNode4 = ListNode.toListNode(new int[]{0,12,17, 19});
        ListNode listNode5 = ListNode.toListNode(new int[]{0,12,17, 19});
        ArrayList<ListNode> arrayList = new ArrayList<>();
        arrayList.add(listNode1);
        arrayList.add(listNode2);
        arrayList.add(listNode3);
        arrayList.add(listNode4);
        arrayList.add(listNode5);
        ListNode.print(new Sample().mergeKLists(arrayList));

    }




    public ListNode mergeKLists(ArrayList<ListNode> a) {
        TreeSet<ListWithIndex> treeSet = new TreeSet<>(new Comparator<ListWithIndex>(){
            @Override
            public int compare(ListWithIndex o1, ListWithIndex o2){
                if (o1.listNode.val < o2.listNode.val){
                    return -1;
                }

                if (o1.listNode.val > o2.listNode.val){
                    return 1;
                }

                return o1.listNode.val - o2.listNode.val;
            }
        });

        ListNode result = new ListNode(-1);
        for (int i = 0; i < a.size(); i++) {
            ListNode head = a.get(i);
            if (head != null) {
                treeSet.add(new ListWithIndex(head, i));
            }
        }

        ListNode head = result;
        while (!treeSet.isEmpty()){
            result.next = treeSet.first().listNode;
            int index = treeSet.first().index;
            ListNode listNode = a.get(index);
            listNode = listNode.next;
            treeSet.remove(treeSet.first());
            if (listNode != null){
                a.set(index, listNode);
                treeSet.add(new ListWithIndex(listNode,index));
            }
            result = result.next;
        }

        return head.next;
    }

    public class ListWithIndex{
        ListNode listNode;
        int index;

        public ListWithIndex(ListNode listNode, int index) {
            this.listNode = listNode;
            this.index = index;
        }
    }

    public ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {
        ArrayList<Integer> result = new ArrayList<>();
        if (A.size() < B) {
            return result;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < B; i++) {
            map.put(A.get(i), map.containsKey(A.get(i)) ? map.get(A.get(i)) + 1 : 1);
        }

        result.add(map.size());
        for (int i = B; i < A.size(); i++) {
            map.put(A.get(i - B), map.get(A.get(i - B)) - 1);
            if (map.get(A.get(i - B)) == 0) {
                map.remove(A.get(i - B));
            }

            map.put(A.get(i), map.containsKey(A.get(i)) ? map.get(A.get(i)) + 1 : 1);
            result.add(map.size());
        }

        return result;
    }

    /*private void heapify(ArrayList<NumberWithIndex> a, int i, int N) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < N && a.get(left).index < a.get(i).index) {
            largest = left;
        }

        if (right < N && a.get(right).index < a.get(left).index) {
            largest = right;
        }

        if (largest != i) {
            swap(a, i, largest);
            heapify(a, largest, N);
        }
    }

    private void heapSort(int[] a) {
        int N = a.length;
        //buildHeap(a, N);
        for (int i = a.length - 1; i >= 0; i--) {
            //  swap(a, 0, i);
            N--;
            //heapify(a, 0, N);
        }
    }

    private void buildHeap(ArrayList<NumberWithIndex> a, int N) {
        for (int i = N / 2 - 1; i >= 0; i--) {
            heapify(a, i, N);
        }
    }

    private void swap(ArrayList<NumberWithIndex> a, int i, int largest) {
        NumberWithIndex temp = a.get(i);
        a.set(i, a.get(largest));
        a.set(largest, temp);
    }*/


}
