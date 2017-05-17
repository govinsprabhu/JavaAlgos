package com.govind.algo.queue;

import java.util.*;
import java.util.Queue;

/**
 * Created by govindp on 11/13/2015.
 */
public class Solution {
    public static void main(String[] args) {
        new Solution().maxInEveryWindow();
    }

    public void maxInEveryWindow() {
        int[] a = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13};
        int k = 4;

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && a[queue.getLast()] < a[i]) {
                queue.removeLast();
            }

            queue.addLast(i);
        }

        for (int i = k; i < a.length; i++) {
            System.out.println(a[queue.getFirst()]);
            if (queue.getFirst() < i - k + 1) {
                queue.removeFirst();
            }

            while (!queue.isEmpty() && a[queue.getLast()] < a[i]) {
                queue.removeLast();
            }

            queue.addLast(i);

        }
    }
}
