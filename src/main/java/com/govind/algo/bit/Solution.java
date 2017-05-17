package com.govind.algo.bit;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by govindp on 11/15/2015.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().encode(new int[]{3, 13, 15}));
    }

    static String encode(int[] sequence) {
        int previous=  sequence[0];
        for (int i = 1; i < sequence.length; i++) {
            int temp = sequence[i];
            sequence[i] -= previous;
            previous = temp;
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < sequence.length; i++) {
            result.add(toBinary(sequence[i]));
        }
        String endResult = "";
        for (int i = 0; i < result.size(); i++) {
            String resultString = "";
            for (int j = 1; j < result.get(i).size(); j++) {
                resultString += result.get(i).get(j);
            }
            resultString = "0" + resultString;
            int resultLength = resultString.length();
            for (int j = 0; j < resultLength - 1; j++) {
                resultString = "1" + resultString;
            }
            endResult += resultString ;
        }

        return endResult;
    }


    public static ArrayList<Integer> toBinary(int number) {
        ArrayList<Integer> binary = new ArrayList<>();
        while (number > 0) {
            binary.add(number & 1);
            number >>= 1;
        }

        Collections.reverse(binary);
        return binary;
    }

    public void getNumberOfBitsFrom1ToN() {
        int n = 6;

        System.out.println(countNoOfBits(n));
    }

    private int countNoOfBits(int n) {

        int m = getLeftMostIndex(n);
        return countNoOfBits(n, m);
    }

    private int countNoOfBits(int n, int m) {
        if (n == 0) {
            return 0;
        }


        m = getNextLeftMostNumber(n, m);
        if (n == (1 << m) - 1) {
            return (m + 1) * (1 << m);
        }

        n -= (1 << m);
        return n + 1 + countNoOfBits(n, m) + (m * (1 << m - 1));
    }

    private int getNextLeftMostNumber(int n, int m) {
        int temp = 1 << m;
        if (n < temp) {
            temp = temp >> 1;
            m--;
        }
        return m;
    }

    private int getLeftMostIndex(int n) {
        int m = 0;
        while (n > 1) {
            n >>= 1;
            m++;
        }
        return m;
    }
}
