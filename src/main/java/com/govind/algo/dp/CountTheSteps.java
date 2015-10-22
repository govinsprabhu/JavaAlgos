package com.govind.algo.dp;

/**
 * Created by govindp on 9/14/2015.
 */
public class CountTheSteps {

    public static void main(String[] args) {
        int[] array = new int[4];
        for( int   i =0; i < array.length; i++){
            array[i] = -1;
        }
        System.out.println(countTheSteps(3, array));
    }

    private static int countTheSteps(int i, int[] ints) {
        if( i < 0){
            return 0;
        }

        if( i == 0){
            return 1;
        }

        if( ints[i] > -1){
            return ints[i];
        }

        ints[i] = countTheSteps(i - 1,ints)+
                countTheSteps( i -2 , ints) +
                countTheSteps( i - 3, ints);
        return ints[i];
    }
}
