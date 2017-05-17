package com.govind.algo.search;

import com.govind.util.arraylist.ArrayListUtils;

import java.util.ArrayList;

/**
 * Created by govindp on 11/6/2015.
 */
public class BinarySearch {
    public void paint() {
        ArrayList<Integer> arrayList = new ArrayListUtils<Integer>().getArrayList(new Integer[]{5, 4, 3, 2});
        int k = 2;
        int t = 1;
        System.out.println(paint(k, t, arrayList));

    }

    public static void main(String[] args) {
        //System.out.println(new BinarySearch().maxElement(new int[]{5,0,1,2,3,4}));
//        new BinarySearch().searchInRotatedArray();
        new BinarySearch().largestInFirstIncreasingThenDecreasing();
    }




    public void largestInFirstIncreasingThenDecreasing(){
        int[] array = {9, 7, 6, 5};
        int l = 0;
        int h = array.length - 1;
        while (l <= h){
            int mid = l + (h - l)/ 2;
            if ((mid == 0 && array[mid] >= array [mid + 1]) || (mid == array.length - 1 && array[mid] >= array[mid - 1]) ||
                    (array[mid] >= array[mid - 1] && array[mid] >= array[mid + 1])){
                System.out.println(array[mid]);
                return;
            }else if (array[l] <= array[mid]){
                l = mid + 1;
            }else {
                h = mid - 1;
            }
        }
    }

    public void largestInSortedRotatedArray() {
        int[] array = {2, 5, 6, 7};
        int l = 0;
        int h = array.length - 1;
        while (l <= h){
            int mid = l +(h - l) /2;
            if ((mid == h) || (array[mid] >= array[mid +1])){
                System.out.println(array[mid]);
                return;
            }else if (array[h] <= array[l]){
                if (array[mid] > array[l]){
                    l = mid + 1;
                }else {
                    h = mid - 1;
                }
            }else{
                l = mid + 1;
            }
        }
        System.out.println("No element found");
    }

    public int paint(int a, int b, ArrayList<Integer> c) {
        int end = 0;
        int max = 0;
        for (int i = 0; i < c.size(); i++) {
            end += c.get(i);
            max = Math.max(max, c.get(i));
        }

        int start = 0;
        int min = Integer.MAX_VALUE;
        for (; start < end; ) {
            int mid = start + (end - start) / 2;

            if (mid > max && isPossible(c, a, mid)) {
                min = Math.min(mid, min);
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return min * b;
    }

    private boolean isPossible(ArrayList<Integer> c, int a, int mid) {
        int count = 1;
        int sum = 0;
        for (int i = 0; i < c.size(); ) {
            if (sum + c.get(i) <= mid) {
                sum += c.get(i);
                i++;
            } else {
                sum = 0;
                count++;

            }
        }

        return a >= count;
    }

    public void searchSortedStringArrayWithSpace() {
        String[] array = {"apple", "", "", "", "bat", "", "", "cat", "", "", "doll"};
        String key = "apple";

        System.out.println(searchSortedStringArray(array, 0, array.length - 1, key));

    }

    public void peakElement() {
        int[] a = {5, 4, 3, 1, 4, 0};
        System.out.println(findPeakElement(a, 0, a.length - 1));
    }

    private int findPeakElement(int[] a, int low, int high) {
        int mid = low + (high - low) / 2;

        if ((mid == a.length - 1 || a[mid] > a[mid + 1]) && (mid == 0 || a[mid] > a[mid - 1])) {
            return mid;
        }

        if (a[mid] > a[mid - 1]) {
            return findPeakElement(a, mid + 1, high);
        }

        return findPeakElement(a, low, mid - 1);

    }

    public void rowWithMaxOnes() {
        int[][] matrix = {{0, 0, 0, 1},
                {0, 1, 1, 1},
                {0, 0, 1, 1},
                {1, 1, 1, 1}};
        int j = 0, k;
        for (k = 0; k < matrix[0].length; k++) {
            if (matrix[0][k] == 1) {
                break;
            }
        }

        if (k == matrix[0].length) {
            j = matrix[0].length - 1;
        } else {
            j = k;
        }
        int maxIndex = 0;

        for (int i = 0; i < matrix.length; i++) {
            while (j >= 0 && matrix[i][j] == 1) {
                j--;
                maxIndex = i;
            }
        }
        System.out.println(maxIndex);
    }


    private int searchSortedStringArray(String[] array, int first, int last, String key) {
        if (first > last) {
            return -1;
        }
        int mid = first + (last - first) / 2;


        if (array[mid].equals("")) {
            int left = mid - 1;
            int right = mid + 1;
            while (left >= first && right <= last) {
                if (!array[left].equals("")) {
                    mid = left;
                    searchSortedStringArray(array, left + 1, last, key);
                    break;
                }
                if (!array[right].equals("")) {
                    mid = right;
                    break;
                }
                left--;
                right++;
            }
        }

        if (array[mid].equals("")) {
            return -1;
        }

        if (array[mid].equals(key)) {
            return mid;
        }

        if (array[mid].compareTo(key) < 0) {
            return searchSortedStringArray(array, mid + 1, last, key);
        }

        return searchSortedStringArray(array, first, mid - 1, key);
    }


    public void searchInRotatedArray() {
        int[] array = {30, 40, 50, 5, 10, 20};
        int key = 20;
        int l = 0;
        int h = array.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (array[m] == key) {
                System.out.print(m);
                break;
            }

            if (array[l] > array[h]) {
                if (key < array[m] && key >= array[l]) {
                    h = m - 1;
                } else if (key > array[m] && key > array[h]) {
                    h = m - 1;
                } else if (key < array[l] || key > array[m]) {
                    l = m + 1;
                }
            } else if (key > array[m]) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
    }

    protected int maxElement(int[] array) {
        int l = 0;
        int h = array.length - 1;
        while (l < h) {
            int mid = l + (h - l) / 2;
            int next = (mid + 1) % array.length;
            if (array[mid] > array[next]) {
                return array[mid];
            }

            if (array[mid] > array[h]) {
                l = mid + 1;
            } else if (array[mid] < array[l]) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }

        }
        return array[0];
    }
}
