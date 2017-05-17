    package com.govind.algo.platform.codility;

import java.util.*;

    /**
 * Created by govindp on 11/27/2015.
 */
public class Solution {
    public static void main(String[] args) {

        Set<Integer> set = new HashSet<>();
        set.add(null);
        set.add(null);
        for (Integer in : set){
            System.out.println(in);
        }
        Iterable<Integer> iterable = (Iterable<Integer>) set.iterator();

        //Iterator
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        Arrays.sort(A);
        int count = 0;
        for (int i = 0; i < A.length - 2; i++) {
            if (A[i] + A[i + 1] > A[i+2]) {
                return 1;
            }
            i++;

        }
        return count;
    }


    public int solution1(int[] A) {
        // write your code in Java SE 8
        if (A.length < 3) {
            return -1;
        }

        int min1 = Integer.MIN_VALUE, min2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE, max1 = Integer.MAX_VALUE, max2 = Integer.MAX_VALUE;
        max1 = Math.max(A[0], Math.max(A[1], A[2]));
        max3 = Math.min(Math.min(A[0], A[1]), A[2]);
        for (int i = 0; i < 3; i++) {
            if (A[i] != max1 && A[i] != max3) {
                max2 = A[i];
            }
        }


        min1 = min2 = max3;

        for (int i = 3; i < A.length; i++) {
            if (A[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = A[i];
            }
            if (A[i] < min1) {
                min2 = min1;
                min1 = A[i];
            }
        }

        return Math.max(max3 * max2 * max1, min1 * min2 * max3);

    }


}
