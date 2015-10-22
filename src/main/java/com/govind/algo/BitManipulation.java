package com.govind.algo;


import java.util.ArrayList;
import java.util.Arrays;
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

        System.out.println(singleNumber(arrayList));

    }


    public static int singleNumber(final List<Integer> a){
        int one = 0, two = 0, common = 0;
        for (int i = 0; i < a.size(); i++){
            two |= (one & a.get(i));

            one  ^= a.get(i);

            common = ~(one & two);

            one &= common;

            two &= common;

        }
        return one;
    }

    public static long divide(long divident, long divisor) {
        long t = 0;
        long q = 0;
        for (long  i = 31; i >= 0; i--){
            if ((t + (divisor << i)) <= divident){
                q |= 1 << i;
                t += divisor << i;
            }
        }
        return q;
    }
}
