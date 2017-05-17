package com.govind.algo.hash;

import com.govind.util.arraylist.ArrayListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by govindp on 11/28/2015.
 */
public class MaxPoints {
    Integer x;
    Integer y;
    public MaxPoints(){

    }
    private HashMap<Double, Integer> hashMap;
    public MaxPoints(int i, int i1) {
        x= i;
        y = i1;
    }

    @Override
    public int hashCode() {
        return 17 * 31 + x + y;
    }

    @Override
    public boolean equals(Object obj) {
        if  (this == obj){
            return true;
        }

        if (!(obj instanceof MaxPoints)){
            return false;
        }
        MaxPoints MaxPoints = (MaxPoints) obj;
        return Integer.compare(MaxPoints.x,x) == 0 &&
                Integer.compare(MaxPoints.y, y)== 0;
    }

    public static void main(String[] args) {
        ArrayList<Integer> x = new ArrayListUtils<Integer>().getArrayList(new Integer[]{1,2,1,2,0,3});
        ArrayList<Integer> y = new ArrayListUtils<Integer>().getArrayList(new Integer[]{3,4,2,3,5,8});
        System.out.println(new MaxPoints().maxPoints(x, y));
    }
    public int maxPoints(ArrayList<Integer> A, ArrayList<Integer> B) {
        hashMap = new HashMap<>();

        if (A == null || B == null)
            return -1;

        if (A.size() == 0)
            return 0;

        int n = A.size();
        int x1, y1, x2, y2;
        int val;
        int max = 0;

        for (int i = 0; i < n; i++) {

            x1 = A.get(i);
            y1 = B.get(i);
            hashMap.clear();

            for (int j = 0; j < n; j++) {

                if (i == j)
                    continue;

                x2 = A.get(j);
                y2 = B.get(j);

                double slope = y2 - y1;
                int den = x2 - x1;

                if (den == 0)
                    slope = Double.POSITIVE_INFINITY;
                else
                    slope = slope / den;

                if (slope==1){
                    System.out.println();
                }

                val = 1;

                if (hashMap.containsKey(slope)) {
                    val = hashMap.get(slope) + 1;
                }

                hashMap.put(slope, val);

            }

            for (Map.Entry<Double, Integer> entry : hashMap.entrySet()) {
                val = entry.getValue();
                max = Math.max(max, val);
            }
        }

        return max + 1;
    }
    private static int gcd(int a, int b){
        int c;
        if (a== 0){
            return b;
        }
        if (b == 0){
            return a;
        }
        a = a < 0 ? a * -1: a;
        b = b < 0 ? b * -1: b;
        while (a != 0 && b != 0 ){
            c = a;
            a = b % a;
            b = c;
        }

        return a == 0 ? b: a;
    }
}
