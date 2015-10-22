package com.govind.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class Solution {

    private static final int MOD = 10000003;

    public static void main(String[] args) {
        System.out.println(fractionToDecimal( 87,22));
    }

    public static String fractionToDecimal(int numerator1, int denominator1) {
        String result = "";
        if ((numerator1 == 0) || (denominator1 == 0)) {
            return  "0";
        }
        if ((numerator1 < 0) ^ (denominator1 < 0)) {
            result += "-";
        }

        long numerator = Math.abs((long)numerator1);;
        long denominator = Math.abs((long)denominator1);
        result += String.valueOf( numerator /  denominator);
        long remainder = numerator % denominator *  10;
        if (remainder == 0) {
            return result;
        }

        result += '.';
        int count = 0;
        StringBuffer stringBuffer = new StringBuffer(result);
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                int index = map.get(remainder);
                return stringBuffer.substring(0, index) + '(' + stringBuffer.substring(index,stringBuffer.length()) +')';
            }

            map.put(remainder,stringBuffer.length());
            stringBuffer.append(remainder/denominator);
            remainder = remainder% denominator * 10;
        }

        return String.valueOf(stringBuffer);

    }

    public static boolean wordPattern(String pattern, String str) {
        Map<Character, String> map = new HashMap<Character, String>();
        int strIndex = 0;
        String[] array = str.split(" ");

        if (pattern.length() != array.length) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            if (!map.containsKey(pattern.charAt(i))) {
                if (map.containsValue(array[i])) {
                    return false;
                }
                map.put(pattern.charAt(i), array[i]);
            } else {
                if (!map.get(pattern.charAt(i)).equals(array[i])) {
                    return false;
                }
            }
        }

        return true;

    }

    public static int book(int numberOfPeople, ArrayList<Integer> bookPages) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < bookPages.size(); i++) {
            end += bookPages.get(i);
        }
        int ans = Integer.MAX_VALUE;
        while (start <= end) {
            int mid = start + ((end - start) / 2);
            if (possible(mid, numberOfPeople, bookPages)) {
                ans = Math.min(ans, mid);
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return (int) ((ans));
    }

    private static boolean possible(long maxTime, int numberOfPeople, ArrayList<Integer> C) {
        if (C.get(C.size() - 1) > maxTime) {
            return false;
        }
        int count = 1;
        int sum = 0;
        for (int i = 0; i < C.size() && count <= numberOfPeople; ) {
            sum += C.get(i);
            if (sum > maxTime) {
                sum = 0;
                count++;
            } else {
                i++;
            }
        }
        if (count <= numberOfPeople) {
            return true;
        }
        return false;

    }


    public static int paint(int A, int B, ArrayList<Integer> C) {

        int start = 0;
        int end = 0;
        for (int i = 0; i < C.size(); i++) {
            end += C.get(i);
        }
        int ans = Integer.MAX_VALUE;
        while (start <= end) {
            int mid = start + ((end - start) / 2);
            if (possible(mid, A, C)) {
                ans = Math.min(ans, mid);
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return (int) ((ans * B) % MOD);

    }

   /* private static boolean possible(long maxTime, int B, ArrayList<Integer> C) {
        if(isGreaterThanLargest(C) > maxTime){
            return false;
        }
        int count = 1;
        int sum = 0;
        for(int i =0; i < C.size() && count <= B; ){
            sum += C.get(i);
            if(sum > maxTime){
                sum  = 0;
                count ++;
            }else{
                i++;
            }
        }
        if(count <= B){
            return true;
        }
        return false;

    }*/

    private static int isGreaterThanLargest(ArrayList<Integer> c) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < c.size(); i++) {
            max = Math.max(max, c.get(i));
        }
        return max;
    }

}

