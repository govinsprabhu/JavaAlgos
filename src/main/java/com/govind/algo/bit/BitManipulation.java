package com.govind.algo.bit;


import com.govind.util.arraylist.ArrayListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by govindp on 9/25/2015.
 */
public class BitManipulation {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(2);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(3);

        new BitManipulation().reverse();
    }

    public void reverse(){
        int number =  43261596 ;
        int result = 0;
        for (int i = 31; i >= 0; i--) {
            if ((number & (1 << i)) != 0){
                result |= 1 << (31 - i);
            }
        }
        System.out.println(result);
    }

    public void decimalToString() {
        float floating = 2.625f;
        String result = "";
        int dec = (int) floating;
        floating = floating - ((int) floating);
        while (true) {
            floating = floating * 2;
            if (result.length() >= 32) {
                break;
            }
            if (floating == 1) {
                result = "1" + result;
                break;
            }

            if (floating >= 1) {
                result = "1" + result;
                floating -= 1;
            } else {
                result = "0" + result;
            }
        }
        result = "." + result;
        while (dec >= 1) {
            if (dec % 2 == 1) {
                result = "1" + result;
            } else {
                result = "0" + result;
            }
            dec /= 2;
        }

        System.out.println(result);
    }

    public void findNextSparce() {
        int number = 38;
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (number > 0) {
            arrayList.add(number & 1);
            number >>= 1;
        }

        int visitedTill = 0;
        for (int i = 1; i < arrayList.size(); i++) {
            if (arrayList.get(i - 1) == 1 && arrayList.get(i) == 1) {
                if (i == arrayList.size() - 1) {
                    arrayList.add(1);
                } else {
                    arrayList.set(i + 1, 1);
                }
                int current = i;
                while (current >= visitedTill) {
                    arrayList.set(current, 0);
                    current--;
                }
                visitedTill = i;
            }
        }

        int power = 1;
        int sum = 0;
        for (int digit : arrayList) {
            sum += (digit * power);
            power *= 2;
        }

        System.out.println(sum);
    }

    public void countSetBitTillN() {
        int n = 7;
        int leftMost = getLeftMost(n);

        System.out.println(countSetBitTillN(n));
    }

    private int countSetBitTillN(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }
        int leftMost = getLeftMost(n);
        if ((n & (n + 1)) == 0) {
            return (leftMost * (1 << (leftMost - 1)));
        }

        n -= ((leftMost - 1) * (1 << (leftMost - 2)));
        return n + countSetBitTillN(n) + ((leftMost - 1) * (1 << (leftMost - 2)));

    }

    private int getLeftMost(int n) {
        int m = 1;
        while (n > 1) {
            n >>= 1;
            m++;
        }

        return m;
    }

    public void divide() {
        long divisor = 3;
        long dividend = 81;


        int t = 0;
        int q = 0;
        for (int i = 31; i >= 0; i--) {
            if ((t + (divisor << i)) <= dividend) {
                t += (divisor << i);
                q |= (1 << i);
            }
        }

        System.out.println(q);
    }

    public void cntBits() {
        ArrayList<Integer> arrayList = new ArrayListUtils<Integer>().getArrayList(new Integer[]{1, 3, 5});
        System.out.println(cntBits(arrayList));
    }


    public int cntBits(ArrayList<Integer> A) {
        long totalCount = 0;
        long size = A.size();
        for (int i = 0; i < 31; i++) {
            int mask = 1;
            mask <<= i;
            long currentCount = 0;
            for (int j = 0; j < A.size(); j++) {
                currentCount += (((A.get(j) & mask) ^ mask) == 0 ? 1 : 0);
            }
            totalCount += (((currentCount * (size - currentCount)) * 2) % 1000000007);
        }


        return (int) totalCount;
    }


    public static int singleNumber(final List<Integer> a) {
        int one = 0, two = 0, common = 0;
        for (int i = 0; i < a.size(); i++) {
            two |= (one & a.get(i));

            one ^= a.get(i);

            common = ~(one & two);

            one &= common;

            two &= common;

        }
        return one;
    }

    public static long divide(long divident, long divisor) {
        long t = 0;
        long q = 0;
        for (long i = 31; i >= 0; i--) {
            if ((t + (divisor << i)) <= divident) {
                q |= 1 << i;
                t += divisor << i;
            }
        }
        return q;
    }
}

