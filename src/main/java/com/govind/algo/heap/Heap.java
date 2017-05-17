package com.govind.algo.heap;

import java.util.Comparator;

/**
 * Created by govindp on 10/23/2015.
 */
public abstract class Heap {
    private int[] array;
    private int index;
    private static int MAX_SIZE = 128;
    private Comparator<Integer> comparator;

    public Heap(Comparator<Integer> comparator) {
        this.array = new int[MAX_SIZE];
        this.index = -1;
        this.comparator = comparator;
    }

    public int[] getArray() {
        return array;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public static int getMaxSize() {
        return MAX_SIZE;
    }

    public static void setMaxSize(int maxSize) {
        MAX_SIZE = maxSize;
    }


    public boolean insert(int data) {
        if (index > MAX_SIZE) {
            return false;
        }
        array[++index] = data;
        hepify(index);
        return true;
    }

    public void hepify(int child) {
        int parent = parent(child);
        if (parent != child && isGreaterOrLesser(child, parent)) {
            swap(array,child,parent);
            hepify(parent);
        }
    }

    private void swap(int[] array, int child, int parent) {
        array[child] = array[child] + array[parent];
        array[parent] = array[child] - array[parent];
        array[child] = array[child] - array[parent];
    }

    public boolean isGreaterOrLesser(int child, int parent) {
        return comparator.compare(array[child], array[parent]) > 0
                ? true
                : false;
    }

    private int parent(int index) {
        if (index == 0) {
            return 0;
        }

        return (index - 1) / 2;
    }


    public int getTop() {
        return index < 0
                ? -1
                : array[0];
    }

    public int removeTop() {
        int temp = array[0];
        array[0] = array[index--];
        hepify(index);
        return temp;
    }


}
