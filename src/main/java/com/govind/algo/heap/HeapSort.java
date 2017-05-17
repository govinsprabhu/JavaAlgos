package com.govind.algo.heap;

/**
 * Created by govindp on 10/23/2015.
 */
public class HeapSort {

    public void sort(int[] array) {
        heapify(array);
        int n = array.length -1;
        for (int i = n; i>= 1; i--){
            swap(array,0,i);
            n--;
            maxHeap(array,0,n);
        }

    }

    private void heapify(int[] array) {
        for (int i = (array.length - 1) / 2; i >= 0; i--) {
            maxHeap(array, i, array.length -1);
        }
    }

    private void maxHeap(int[] array, int i, int N) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int max = i;
        if (left <= N &&array[max] < array[left]) {
            max = left;
        }

        if (right <= N &&array[right] > array[max]) {
            max = right;
        }
        if (max != i) {
            swap(array, i, max);
            maxHeap(array, max, N);
        }
    }

    private void swap(int[] array, int i, int max) {
        array[i] = array[max] + array[i];
        array[max] = array[i] - array[max];
        array[i] = array[i] - array[max];
    }

}
