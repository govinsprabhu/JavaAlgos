package com.govind.algo.sorting;

import com.govind.util.arraylist.ArrayListUtils;


import java.util.*;

/**
 * Created by govindp on 9/8/2015.
 */
public class Solution {
    public static void main(String[] args) {
        Integer[] spam = new Integer[]{19, 20, 21, 22, 28, 29, 32, 36, 39, 40, 41, 42, 43, 45, 48, 49, 51, 54, 55, 56, 58, 60, 61, 62, 65, 67, 69, 71, 72, 74, 75, 78, 81, 84, 85, 87, 89, 92, 94, 95, 96, 97, 98, 99, 100, 105, 107, 108, 109, 110, 112, 113, 115, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 128, 130, 131, 133, 134, 135, 136, 137, 138, 139, 141, 142, 144, 146, 147, 148, 149, 150, 153, 155, 157, 159, 161, 163, 164, 169, 170, 175, 176, 179, 180, 185, 187, 188, 189, 192, 196, 199, 201, 203, 205, 3, 7, 9, 10, 12, 13, 17};
        ArrayList<Integer> sortedArray = new ArrayList<>(Arrays.asList(spam));
        int[][] a = {{15, 20, 40, 85}, {20, 35, 80, 95}, {30, 55, 95, 105}, {40, 80, 100, 120}};
//        System.out.println(findInArray(a, 55));

        ArrayList<Integer> sortedArray1 = new ArrayList<>();
        //sortedArray1.add(2);
/*
        sortedArray1.add(4);
        sortedArray1.add(4);
        sortedArray1.add(4);
*/

        sortedArray1.add(1);
        sortedArray1.add(12);
        sortedArray1.add(15);
        sortedArray1.add(26);
        //sortedArray1.add(38);
        ArrayList<Integer> sortedArray2 = new ArrayList<>();
        sortedArray2.add(2);
        sortedArray2.add(13);
        sortedArray2.add(17);
        sortedArray2.add(30);
        new Solution().sortRespectToAnother();
    }

    public void sortRespectToAnother() {
        int[] a = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8};
        int[] a1 = {2, 1, 8, 3};
        sortRespectToAnother(a, a1);
    }

    private void sortRespectToAnother(int[] a, int[] a1) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a1.length; i++) {
            map.put(a1[i], i);
        }


        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (map.containsKey(a[i])) {
                    if (map.containsKey(a[j])) {
                        if (map.get(a[j]) < map.get(a[i])) {
                            swap(a, i, j);
                        }
                    }
                } else if (a[i] > a[j]) {
                    swap(a, i, j);
                }
            }
        }


        for (int e : a){
            System.out.print(e+" ");
        }
    }


    public void sortNegativeAndPositiveApart() {
        int[] array = {-2, 3, 4, 5, -1, -6, 7, 9, 1};

        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (0 > array[i]) {
                swap(array, i, j);
                j++;

            }

        }

        for (int i = 0; i < array.length; i++) {

        }

        for (int a1 : array) {
            System.out.print(a1);
        }
    }

    public void findKthLargest() {
        int[] a = {7, 10, 4, 3, 20, 15};
        int k = 1;
        partition(a, k, 0, a.length - 1);

    }

    private void partition(int[] a, int k, int start, int end) {
        if (end < start) {
            return;
        }

        int index = partitionByMid(a, start, end);
        if (index == k - 1) {
            System.out.println(a[index]);
            return;
        }

        partition(a, k, index + 1, end);
        partition(a, k, start, index - 1);
    }

    private int partitionByMid(int[] a, int start, int end) {
        int i = start;
        int key = a[end];
        for (int j = start; j <= end - 1; j++) {
            if (a[j] <= key) {
                swap(a, i, j);
                i++;
            }
        }

        swap(a, i, end);
        return i;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void sortNegativeAndPositiveAlternatively() {
        int[] a = {1, 2, 3, -4, -1, 4};
        int outOfIndex = -1;
        for (int i = 0; i < a.length; i++) {
            if (outOfIndex != -1) {
                if ((a[i] < 0 && a[outOfIndex] > 0) ||
                        (a[i] >= 0 && a[outOfIndex] < 0)) {
                    rightShift(a, i, outOfIndex);
                    if (i - outOfIndex > 2) {
                        outOfIndex = outOfIndex + 2;
                    } else {
                        outOfIndex = -1;
                    }
                }
            }

            if (outOfIndex == -1) {
                if ((a[i] < 0 && (i & 1) == 1) ||
                        (a[i] >= 0 && (i & 1) == 0)) {
                    outOfIndex = i;
                }
            }
        }

        for (int element : a) {
            System.out.print(element + "  ");
        }
    }

    private void rightShift(int[] a, int i, int outOfIndex) {
        int temp = a[i];
        for (int j = i; j > outOfIndex; j--) {
            a[j] = a[j - 1];
        }
        a[outOfIndex] = temp;
    }

    public ArrayList<Integer> sortAccordingToAnotherArray(ArrayList<Integer> arrayList1, ArrayList<Integer> arrayList2) {
        Collections.sort(arrayList1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        return arrayList1;
    }

    public static int power(int a, int b, int c) {
        long ans = 1;
        long base = a;
        while (b > 0) {
            if (b % 2 == 1) {
                ans = (ans * base) % c;
                b--;
            } else {
                base = (base * base) % c;
                b >>= 1;
            }
        }
        return (int) (ans + c) % c;
    }

    private static boolean findInArray(int[][] a, int key) {
        int c = a[0].length - 1;
        int r = 0;
        while (r < a.length && c >= 0 && r >= 0) {
            if (a[r][c] == key) {
                return true;
            }
            if (a[r][c] > key) {
                c--;
            } else {
                r++;
            }
        }

        return false;
    }


    private static int medianOfSorted(List<Integer> arrayList1, List<Integer> arrayList2, int n) {
        if (n <= 0) {
            return -1;
        }
        if (n == 1) {
            return (arrayList1.get(0) + arrayList2.get(0)) / 2;
        }
        if (n == 2) {
            return (Math.max(arrayList1.get(0), arrayList2.get(0)) + Math.min(arrayList1.get(1), arrayList2.get(1))) / 2;
        }
        int m1 = median(arrayList1, n);
        int m2 = median(arrayList2, n);

        if (m1 < m2) {
            if (n % 2 == 0) {
                return medianOfSorted(arrayList1.subList(n / 2 - 1, arrayList1.size()), arrayList2.subList(0, n - n / 2 + 1), n - n / 2 + 1);
            }
            return medianOfSorted(arrayList1.subList(n / 2, arrayList1.size()), arrayList2.subList(0, n - n / 2), n - n / 2);
        }

        if (n % 2 == 0) {
            return medianOfSorted(arrayList1.subList(0, n - n / 2 - 1), arrayList2.subList(n / 2 - 1, arrayList2.size() - 1), n - n / 2 + 1);
        }
        return medianOfSorted(arrayList1.subList(0, n - n / 2), arrayList2.subList(n / 2, arrayList2.size()), n - n / 2);
    }

    private static int median(List<Integer> arrayList1, int n) {
        if (n % 2 == 0) {
            return (arrayList1.get(n / 2) + arrayList1.get(n / 2 - 1)) / 2;
        }
        return arrayList1.get(n / 2);
    }


    private static int binarySearchRotatedArray(ArrayList<Integer> arrayList, int key) {
        int l = 0;
        int h = arrayList.size() - 1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (arrayList.get(mid) == key) {
                return mid;
            }
            if (arrayList.get(l) < arrayList.get(mid)) {
                if (key > arrayList.get(mid)) {
                    l = mid + 1;
                } else if (key < arrayList.get(mid) && key >= arrayList.get(l)) {
                    h = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else if (arrayList.get(mid) < arrayList.get(h)) {
                if (key > arrayList.get(mid) && key <= arrayList.get(h)) {
                    l = mid + 1;
                } else {
                    h = mid - 1;
                }
            } else if (arrayList.get(mid) < key) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return -1;
    }

    private static int square(int x, int n, int p) {

        if (n == 0) {
            return 1;
        }
        int ans = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                ans = (ans * x) % p;
                n--;
            } else {
                x = (x * x) % p;
                n /= 2;
            }
        }

        return ans;
    }

    private static int remainder(int x, int n, int d) {
        int ans = 1;
        int base = x;
        while (n > 0) {
            if (n % 2 == 1) {
                ans = ans * base;
                n--;
            } else {
                base = base * base;
                n >>= 1;
            }
        }
        return ans;
    }

    private static int paintersTime(int painters, int time, ArrayList<Integer> boards) {
        for (int i = 0; i < boards.size(); i++) {

        }
        return 0;
    }

    private static int squareRoot(int number) {
        long previous = 1;
        for (long i = 1; i <= number; i *= 2) {
            long square = i * i;
            if (square == number) {
                return (int) i;
            }
            if (square >= number) {
                long prePrevious = previous;
                for (long j = previous; j <= i; j++) {
                    if (j * j == number) {
                        return (int) j;
                    }
                    if (j * j >= number) {
                        return (int) prePrevious;
                    }
                    prePrevious = j;
                }
            }
            previous = i;
        }
        return 0;

    }

    private static int squareRoot2(int number) {
        int start = 1;
        int end = number;
        int ans = 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (mid * mid <= number) {
                start = mid + 1;
                ans = mid;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }

    private static Integer indexOfNumber(List<Integer> a, int l, int h, int key) {
        int n = a.size();
        while (l <= h) {
            int mid = (l + h) / 2;
            if (a.get(mid) == key) {
                return mid;
            }
            //int next = ( mid + 1) % n;
            int previous = (mid + n - 1) % n;
            if (mid == 0) {
                return 0;
            }
            if (key > a.get(previous) && key < a.get(mid)) {
                return mid;
            }
            if (mid == n - 1) {
                return mid + 1;
            }
            if (key <= a.get(previous) && key < a.get(mid)) {
                h = mid - 1;
            } else if (key > a.get(previous) && key > a.get(mid)) {
                l = mid + 1;
            }
        }
        return -1;
    }

    private static Integer startingAndEnding(List<Integer> a, int l, int h, boolean isFirst, int key) {
        int index = -1;
        while (l <= h) {
            int mid = (l + h) / 2;
            if (a.get(mid) == key) {
                index = mid;
                if (isFirst) {
                    h = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else if (a.get(mid) < key) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }

        }
        return index;

    }

    /* public static int findCount(final List<Integer> a, int b) {
         int firstIndex = binarySearch(a, b, 0, a.size() - 1);
         int lastIndex =  binarySearch(a, b, 0, a.size() -1);

         return  lastIndex - firstIndex + 1;
     }
 */
    private static int binarySearch(List<Integer> a, Integer key, int l, int h) {
        while (l <= h) {
            int mid = (l + h) / 2;
            if (a.get(mid) == key) {
                return mid;
            }
            if (a.get(l) < a.get(mid) && a.get(l) > a.get(h)) {
                if (key > a.get(l)) {
                    h = mid - 1;
                } else if (key > a.get(mid)) {
                    l = mid + 1;
                } else {
                    l = mid + 1;
                }
            } else if (a.get(l) > a.get(h)) {
                if (a.get(mid) > key) {
                    h = mid - 1;
                } else if (key > a.get(h)) {
                    h = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else if (a.get(mid) > key) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;

    }

    private static Integer largestNumber(List<Integer> a, int l, int h) {
        if (a.size() == 1) {
            return a.get(0);
        }
        while (l <= h) {
            int mid = (l + h) / 2;
            //System.out.println(a.get(mid));

            if (mid == 0 || a.get(mid) < a.get(mid - 1)) {
                return a.get(mid);
            }
            if (l + 1 == h) {
                return a.get(h);
            }
            if (a.get(l) < a.get(mid) && a.get(l) > a.get(h)) {
                l = mid + 1;
            } else if (a.get(l) > a.get(h)) {
                h = mid - 1;
            } else {
                h = mid - 1;
            }
        }
        return -1;

    }

    private static Integer smallestNumber(List<Integer> a, int l, int h) {
        int n = a.size();
        while (l <= h) {
            if (a.get(l) <= a.get(h)) {
                return a.get(l);
            }
            int mid = (l + h) / 2;
            int next = (mid + 1) % n;
            int previous = (n + mid - 1) % mid;
            if (a.get(mid) < a.get(next) && a.get(mid) < a.get(previous)) {
                return a.get(mid);
            } else if (a.get(mid) < a.get(h)) {

            }

        }
        return -1;

    }


}
