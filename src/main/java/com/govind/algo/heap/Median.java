package com.govind.algo.heap;

/**
 * Created by govindp on 10/23/2015.
 */
public class Median {

    MinHeap minHeap;
    MaxHeap maxHeap;

    public Median() {
        this.minHeap = new MinHeap();
        this.maxHeap = new MaxHeap();
    }

    public static void main(String[] args) {

        Median median = new Median();
        median.minHeap.insert(10);
        median.maxHeap.insert(5);
        System.out.println(median.getMedian(15));
        System.out.println(median.getMedian(25));
        System.out.println(median.getMedian(4));
        System.out.println(median.getMedian(9));
        System.out.println(median.getMedian(30));
    }

    public int getMedian(int number) {
        int compare = compare(maxHeap.getIndex(), minHeap.getIndex());
        switch (compare) {
            case 1:
                if (number < maxHeap.getTop()) {
                    minHeap.insert(maxHeap.removeTop());
                    maxHeap.insert(number);
                } else {
                    maxHeap.insert(number);
                    maxHeap.hepify(maxHeap.getIndex());
                }
                return average();
            case 0:
                if (number < maxHeap.getTop()) {
                    maxHeap.insert(number);
                } else {
                    minHeap.insert(number);
                }
                return maxCountTop();
            case -1:
                if (number < maxHeap.getTop()){
                    maxHeap.insert(number);
                }else {
                    maxHeap.insert(minHeap.removeTop());
                    minHeap.insert(number);
                }
                return average();
        }

        return -1;
    }

    private int average() {
        return (maxHeap.getTop() + minHeap.getTop()) / 2;
    }

    private int maxCountTop() {
        return maxHeap.getIndex() > minHeap.getIndex()
                ? maxHeap.getTop()
                : minHeap.getTop();
    }

    private int compare(int index, int index1) {
        return index > index1
                ? 1
                : index == index1
                ? 0
                : -1;
    }
}
