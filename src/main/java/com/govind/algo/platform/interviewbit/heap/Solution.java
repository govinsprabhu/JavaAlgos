package com.govind.algo.platform.interviewbit.heap;

import java.util.HashMap;

/**
 * Created by 609600403 on 25/04/2016.
 */
public class Solution {
    int capacity;
    int currencapacity;
    HashMap<Integer, DoubleLinkedList> map;
    DoubleLinkedList head = null;
    DoubleLinkedList tail = null;


    public Solution(int capacity) {
        map = new HashMap<>();
        head = new DoubleLinkedList(Integer.MAX_VALUE, 0);
        tail = new DoubleLinkedList(Integer.MIN_VALUE, 0);
        this.capacity = capacity;
        currencapacity = 0;
        DoubleLinkedList.addToFirst(head, tail);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        DoubleLinkedList doubleLinkedList = map.get(key);
        DoubleLinkedList.removeAndAddToFirst(head, doubleLinkedList);
        return doubleLinkedList.data;
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            DoubleLinkedList.remove(map.get(key));
            currencapacity --;
        }
        if (capacity == currencapacity) {
            map.remove(tail.left.key);
            DoubleLinkedList.removeFromLast(tail.left, tail);
            currencapacity--;
        }
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList(value, key);
        DoubleLinkedList.addToFirst(head, doubleLinkedList);
        map.put(key, doubleLinkedList);
        currencapacity++;
    }

    public static void main(String[] args) {
        String input = "G 2 S 2 6 G 1 S 1 5 S 1 2 G 1 G 2";
        String[] tokens = input.split(" ");
        Solution solution = new Solution(2);
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("S")) {
                solution.set(Integer.parseInt(tokens[i + 1]), Integer.parseInt(tokens[i + 2]));
                i += 2;
            }
            if (tokens[i].equals("G")) {
                System.out.println(solution.get(Integer.parseInt(tokens[i + 1])));
                i += 1;
            }
        }
    }
}

class DoubleLinkedList {
    int data;
    int key;
    DoubleLinkedList left;
    DoubleLinkedList right;

    public DoubleLinkedList(int data, int key) {
        this.data = data;
        this.key = key;
    }

    public static DoubleLinkedList addToFirst(DoubleLinkedList head, DoubleLinkedList doubleLinkedList) {
        DoubleLinkedList next = head.right;
        head.right = doubleLinkedList;
        doubleLinkedList.left = head;
        doubleLinkedList.right = next;
        if (next != null)
            next.left = doubleLinkedList;
        return doubleLinkedList;
    }

    public static void removeAndAddToFirst(DoubleLinkedList head, DoubleLinkedList doubleLinkedList) {
        remove(doubleLinkedList);
        addToFirst(head, doubleLinkedList);
    }

    public static void remove(DoubleLinkedList doubleLinkedList) {
        DoubleLinkedList previous = doubleLinkedList.left;
        DoubleLinkedList next = doubleLinkedList.right;
        previous.right = next;
        next.left = previous;
    }

    public static void removeFromLast(DoubleLinkedList last, DoubleLinkedList tail) {
        DoubleLinkedList previous = last.left;
        previous.right = tail;
        tail.left = previous;
        last.right = null;
    }
}
