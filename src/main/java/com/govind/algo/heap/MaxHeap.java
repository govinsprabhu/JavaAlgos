package com.govind.algo.heap;

import java.util.Comparator;

/**
 * Created by govindp on 10/23/2015.
 */
public class MaxHeap extends Heap {
    public MaxHeap() {
        super(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2
                        ? 1
                        : -1;
            }
        });
    }
}
