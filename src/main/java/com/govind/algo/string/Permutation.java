package com.govind.algo.string;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by govindp on 9/4/2015.
 */
public class Permutation {
    //private static int count;
    //private static boolean isFound;
    public static void main(String[] args) {
        String string = "ZCSFLVHXRYJQKWABGT";
        char[] sorted = string.toCharArray();
        Arrays.sort(string.toCharArray());
        //System.out.println(string);


        //System.out.println(new String(string).equals("abc"));
        System.out.println(findRank(string));
    }

    public static int findRank(String A) {
        //A =A.toUpperCase();
        int[] countArray = new int[256];
        for(int i = 0; i < A.length(); i ++){
            countArray[(A.charAt(i))]++;
        }
        List<Long> factorial = new ArrayList<>();
        factorial.add((long) 1);
        long fact = 1;
        for(long i = 1; i < A.length(); i++){
            fact *= i;
            factorial.add(fact%1000003);
        }
        long rank = 1;
        for(int i = 0; i < A.length(); i++){
            char currentCharacter = A.charAt(i);
            int lessNumbers = 0;
            for(int index = 0; index < currentCharacter; index++){
                lessNumbers += countArray[index];
            }
            rank = (rank +(long) (lessNumbers * factorial.get(A.length() - 1 - i)))%1000003;
            countArray[A.charAt(i)] --;

        }

        return (int) (rank) ;

    }

}
