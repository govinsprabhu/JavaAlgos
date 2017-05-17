package com.govind.algo.linkedlist;

import com.govind.util.arraylist.ArrayListUtils;

import java.util.*;


/**
 * Created by govindp on 9/25/2015.
 */
public class TwoPointers {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList();

        arrayList.add(5);
        arrayList.add(4);
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(7);
        arrayList.add(6);
        arrayList.add(3);
        new TwoPointers().maxone();
    }

    public void pair(){
        List<Integer> array = new ArrayListUtils<Integer>().getArrayList(new Integer[]{3,4,7,1,2,9,8});
        Collections.sort(array);
        for (int i = 0; i < array.size(); i++) {
            for (int j = i ; j < array.size(); j++) {

            }
        }
    }

    public void maxone() {
        ArrayList<Integer> arrayList = new ArrayListUtils<Integer>().getArrayList(new Integer[]{1,1,0,0 });
        System.out.println(maxone1(arrayList, 1));
    }

    public ArrayList<Integer> maxone1(ArrayList<Integer> a, int b) {
        int count = b;
        int maxLength = Integer.MIN_VALUE;
        int endIndex = 0;
        int start = -1;
        int startIndex = 0;
        for (int end = 0; end < a.size(); end++) {
            if (a.get(end) == 0) {
                count--;
            }

            if (count == -1) {
                if (end - start + 1 > maxLength) {
                    endIndex = end;
                    maxLength = end - start +1 ;
                    startIndex = start + 1;
                }
                start++;
                while (start < a.size() && a.get(start) != 0){
                    start++;
                }
                count++;
            }
        }

        Sample sample = new Sample();
        Sample.SampleDu sampleDu = new Sample.SampleDu();


        if (a.size() - start > maxLength && count >= 0) {
            endIndex = a.size();
            maxLength = a.size();
            startIndex = start+ 1;
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = startIndex; i <  endIndex; i++) {
            result.add(i);
        }

        return result;
    }

    public static int numRange(ArrayList<Integer> a, int b, int c) {
        int count = 0;
        for (int i = 0; i < a.size(); i++) {
        }

        return count;
    }

    public static int kthsmallest(final List<Integer> a, int k) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            arrayList.add(a.get(i));
        }

        Collections.sort(arrayList);
        return arrayList.get(k - 1);
    }

    public static int minimize(final List<Integer> a, final List<Integer> b, final List<Integer> c) {
        int i, j, k;
        i = j = k = 0;
        int minValue = Integer.MAX_VALUE;
        while (i < a.size() && j < b.size() && k < c.size()) {
            int ab = Math.abs(a.get(i) - b.get(j));
            int bc = Math.abs(b.get(j) - c.get(k));
            int ca = Math.abs(c.get(k) - a.get(i));
            int max = Math.max(ab, Math.max(bc, ca));
            if (max == ab) {
                if (a.get(i) > b.get(j)) {
                    j++;
                } else {
                    i++;
                }
            } else if (max == bc) {
                if (b.get(j) > c.get(k)) {
                    k++;
                } else {
                    j++;
                }
            } else if (max == ca) {
                if (c.get(k) > a.get(i)) {
                    i++;
                } else {
                    k++;
                }
            }
            minValue = Math.min(minValue, max);
        }

        return minValue;
    }

    public static int maxArea(ArrayList<Integer> a) {
        if (a.size() == 1) {
            return 0;
        }
        int maxArea = Integer.MIN_VALUE;
        int last = a.size() - 1;
        int i = 0;
        for (; i < last; ) {
            int base = last - i;
            int height = 0;
            if (a.get(last) > a.get(i)) {
                height = a.get(i);
                i++;
            } else {
                height = a.get(last);
                last--;
            }
            maxArea = Math.max(maxArea, (base * height));
        }
        return maxArea;
    }


    public static ArrayList<Integer> maxone(ArrayList<Integer> a, int b) {
        int start = 0;
        int count = 0;
        int maxStart = 0;
        int maxLength = -1;
        int end = 0;
        for (; end < a.size(); end++) {
            if (a.get(end) == 0) {
                count++;
            }
            if (count > b) {
                if (end - start > maxLength) {
                    maxStart = start;
                    maxLength = end - start;
                }
                while (end >= start && count > b) {
                    if (a.get(start) == 0) {
                        count--;
                    }
                    start++;
                }
            }
        }

        if (maxLength < (end - start)) {
            maxStart = start;
            maxLength = end - start;
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = maxStart; i < maxLength + maxStart; i++) {
            result.add(i);
        }

        return result;
    }

    public static void sortColors(ArrayList<Integer> a) {

        int[] array = new int[3];
        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }

        for (int i = 0; i < a.size(); i++) {
            array[a.get(i)]++;
        }
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i]; j++) {
                a.set(index++, i);
            }
        }
    }

    public static int removeElement(ArrayList<Integer> a, int b) {
        int previous = 0;
        int count = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != b) {
                a.set(previous++, a.get(i));
                count++;
            }

        }

        ArrayListUtils.printArray(a, count);
        return a.size();
    }

    public static int removeDuplicates(ArrayList<Integer> a) {
        if (a.size() == 1 || a.size() == 2) {
            return a.size();
        }
        Integer previous = 0;
        int count = 1;

        for (int i = 1; i < a.size(); i++) {
            if (a.get(previous).equals(a.get(i))) {
                count++;
            }
            if (!a.get(previous).equals(a.get(i)) || (count == 2)) {
                if (!a.get(previous).equals(a.get(i))) {
                    count = 1;
                }
                a.set(++previous, a.get(i));
            }
        }
        return previous;
    }

    public static void merge(ArrayList<Integer> a, ArrayList<Integer> b) {
        int i = 0;
        int j = 0;
        while (i < a.size() && j < b.size()) {
            if (a.get(i) > b.get(j)) {
                a.add(i, b.get(j));
                j++;
            } else {
                i++;
            }
        }
        while (j < b.size()) {
            a.add(b.get(j));
            j++;
        }
    }


    public static ArrayList<Integer> intersect(final List<Integer> a, final List<Integer> b) {
        int i = 0;
        int j = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (i < a.size() && j < b.size()) {
            if (a.get(i).equals(b.get(j))) {
                arrayList.add(a.get(i));
                i++;
                j++;
            } else if (a.get(i) < b.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return arrayList;
    }

    public static int diffPossible(ArrayList<Integer> a, int b) {

        int i = 0;
        int j = i + 1;
        while (j < a.size()) {

            int diff = a.get(j) - a.get(i);
            if (diff == b) {
                return 1;
            } else if (diff < b) {
                j++;
            } else {
                i++;
                if (i == j) {
                    j++;
                }
            }
        }

        return 0;
    }

    public static ArrayList<ArrayList<Integer>> threeSumClosest(ArrayList<Integer> a) {
        Collections.sort(a);
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        //int closest = Integer.MAX_VALUE - 1000;
        for (int i = 0; i < a.size(); i++) {
            int j = i + 1;
            int last = a.size() - 1;
            ArrayList<Integer> zeroList = new ArrayList<>();
            while (j < last) {
                int sum = (a.get(i) + a.get(j) + a.get(last));
                if (sum == 0) {
                    zeroList.add(a.get(i));
                    zeroList.add(a.get(j));
                    zeroList.add(a.get(last));
                    if (!arrayList.contains(zeroList))
                        arrayList.add(zeroList);
                    j++;
                    last--;
                    zeroList = new ArrayList<>();
                    //break;
                } else if (sum < 0) {
                    j++;
                } else {
                    last--;
                }
            }

        }
        //Collections.sort(arrayList);
        return arrayList;
    }
}
