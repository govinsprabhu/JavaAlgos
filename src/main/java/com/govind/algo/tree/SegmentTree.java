package com.govind.algo.tree;

/**
 * Created by govindp on 12/16/2015.
 */
public class SegmentTree {
    public static void main(String[] args) {
        new SegmentTree().testSegmentTreeForRangeMinimumQuery();
    }

    public void testSegmentTreeForRangeMinimumQuery() {
        int[] array = {2, 5, 1, 4, 9, 3};
        int height = (int) (Math.log(array.length) / Math.log(2)) + ((Math.log(array.length) % Math.log(2) == 0) ? 0 : 1);
        System.out.println(height);
        int maxArraySize = (int) (2 * Math.pow(2, height));
        int[] segmentTree = new int[maxArraySize];
        constructTreeForRMQ(array, 0, array.length - 1, segmentTree, 0);
        for (int element : segmentTree) {
            System.out.print(element + " ");
        }
        System.out.println();

        System.out.println(findMinimumInRange(array, 4, 4, segmentTree));
    }

    private int findMinimumInRange( int[] array,int low, int high, int[] segmentTree) {
        return findMinimumInRange(0, array.length  -1, low, high, segmentTree, 0);
    }

    private int findMinimumInRange(int lowIndex, int highIndex, int low, int high, int[] segmentTree, int index) {
        if (low <= lowIndex && highIndex <= high) {
            return segmentTree[index];
        }

        if (lowIndex >= highIndex) {
            return Integer.MAX_VALUE;
        }

        int mid = mid(lowIndex, highIndex);
        return Math.min(findMinimumInRange(lowIndex, mid, low, high, segmentTree, 2 *index  + 1),
                findMinimumInRange(mid + 1, highIndex, low, high, segmentTree, 2 * index + 2));
    }


    private int constructTreeForRMQ(int[] array, int low, int high, int[] segmentTree, int index) {

        if (low == high) {
            segmentTree[index] = array[low];
            return array[low];
        }

        if (low > high) {
            return Integer.MAX_VALUE;
        }

        int mid = mid(low, high);
        segmentTree[index] = Math.min(constructTreeForRMQ(array, low, mid, segmentTree, 2 * index + 1),
                constructTreeForRMQ(array, mid + 1, high, segmentTree, 2 * index + 2));

        return segmentTree[index];
    }


    public void testSegmentTreeForSubArraySum() {
        int[] array = {1, 3, 5, 7, 9, 11};
        int height = (int) (Math.log(array.length) / Math.log(2)) + ((Math.log(array.length) % Math.log(2) == 0) ? 0 : 1);
        System.out.println(height);
        int maxArraySize = (int) (2 * Math.pow(2, height));
        int[] segmentTree = new int[maxArraySize];
        constructTree(array, segmentTree, 0, array.length - 1, 0);

        for (int element : segmentTree) {
            System.out.print(element + " ");
        }

        System.out.println(search(segmentTree, 0, array.length - 1, 1, 3, 0));

        updateResult(segmentTree, 1, 10, array);
        System.out.println(search(segmentTree, 0, array.length - 1, 1, 3, 0));
    }

    private void updateResult(int[] segmentTree, int index, int value, int[] array) {
        int diff = value - array[index];
        updateResult(segmentTree, index, diff, 0, array.length - 1, 0);
    }

    private void updateResult(int[] segmentTree, int index, int diff, int start, int end, int segmentIndex) {

        if (start == end && start == index) {
            segmentTree[segmentIndex] = segmentTree[segmentIndex] + diff;
            return;
        }

        if (start >= end) {
            return;
        }

        segmentTree[segmentIndex] = segmentTree[segmentIndex] + diff;

        int mid = mid(start, end);
        updateResult(segmentTree, index, diff, start, mid, 2 * segmentIndex + 1);
        updateResult(segmentTree, index, diff, mid + 1, end, 2 * segmentIndex + 2);
    }


    public int constructTree(int[] array, int[] segmentTree, int low, int high, int index) {

        if (low > high) {
            return 0;
        }


        if (low == high) {
            segmentTree[index] = array[low];
            return array[low];
        }


        int mid = mid(low, high);
        segmentTree[index] = constructTree(array, segmentTree, low, mid, 2 * index + 1) +
                constructTree(array, segmentTree, mid + 1, high, 2 * index + 2);

        return segmentTree[index];
    }

    public int search(int[] segmentTree, int low, int high, int startRange, int endRange, int index) {
        if (startRange <= low && high <= endRange) {
            return segmentTree[index];
        }

        if (endRange < low || high < startRange) {
            return 0;
        }

        int mid = mid(low, high);
        return search(segmentTree, low, mid, startRange, endRange, 2 * index + 1) +
                search(segmentTree, mid + 1, high, startRange, endRange, 2 * index + 2);

    }

    private int mid(int low, int height) {
        return low + (height - low) / 2;
    }


}
