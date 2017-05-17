package com.govind.algo.platform.gfg;

import com.govind.util.array.ArraysUtils;

/**
 * Created by 609600403 on 10/07/2016.
 */
public class SegmentedTree {
    int[] array;

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        SegmentedTree segmentedTree = new SegmentedTree(array);
        segmentedTree.print();
        System.out.println(segmentedTree.getSum( 1,2,array.length));
        segmentedTree.update(0, array.length - 1, 7 - array[1], 0, 1);
        segmentedTree.print();
    }


    public void update(int start, int end, int diff, int index, int updatingIndex){
        if (start > updatingIndex  || end < updatingIndex ){
            return;
        }
        array[index] += diff;
        if (start == end && end == updatingIndex){
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, diff, 2 * index+ 1, updatingIndex);
        update(mid + 1, end, diff, 2 * index + 2, updatingIndex);
    }

    public SegmentedTree(int[] input){
        int height = (int)Math.ceil(Math.log(input.length + 1) / Math.log(2));
        int maxSize = 2 * (int) Math.pow(2, height) - 1;
        array = new int[maxSize];
        construct(array, input, 0, input.length - 1, 0);
    }

    private int construct(int[] array, int[] input, int start, int end, int index) {
        if (start > end){
            return 0;
        }

        if (start == end){
            array[index] = input[start];
            return input[start];
        }

        int mid = start + (end - start) / 2;
        array[index] = construct(array, input, start, mid, 2 * index + 1) + construct(array, input, mid + 1, end, 2 * index + 2 );
        return array[index];
    }

    public int getSum(int start, int end, int n){
        if (start < 0 || end > n - 1 || start > end) {
            return 0;
        }
        return getSum(start, end, 0, 0, n - 1);
    }

    private int getSum(int qStart, int qEnd, int index, int s, int e){
        if (qStart <= s && qEnd >= e){
            return  array[index];
        }

        if (qStart > e || s > qEnd){
            return 0;
        }


        int mid = (e + s) / 2;
        return getSum(qStart, qEnd, 2 * index + 1,s, mid ) + getSum(qStart, qEnd, 2 * index + 2, mid + 1, e);
    }

    public void print(){
        ArraysUtils.print(array);
    }


}
