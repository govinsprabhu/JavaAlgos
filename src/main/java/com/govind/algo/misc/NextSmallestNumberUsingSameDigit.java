package com.govind.algo.misc;

/**
 * Created by govindp on 11/5/2015.
 */
public class NextSmallestNumberUsingSameDigit {
    public static void main(String[] args) {
        new NextSmallestNumberUsingSameDigit().nextLargest(new int[]{11, 13, 21, 3,9});
    }

    public void nextLargest(int[] array){
        int max = -1;
        int previous = array[array.length - 1];
        for (int i = array.length- 1; i >= 0; i--) {
            System.out.println(max);
            if (previous <= array[i]){
                max = array[i];
            }
            previous = array[i];
        }

    }

    public int[] nextSmallest(int[] number) {

        int previous = number[number.length - 1];
        int i;
        for (i = number.length - 2; i >= 0; i--) {
            if (number[i + 1] > number[i]) {
                break;
            }
        }
        if (i == 0){
            System.out.println("no number");
            return number;
        }

        int min = number[i + 1];
        int minIndex = i + 1;
        for (int j = i + 2; j < number.length; j++) {
            if (min > number[j]) {
                min = number[j];
                minIndex = j;
            }
        }

        int temp = number[i];
        number[i] = number[minIndex];
        number[minIndex] = temp;

        for (int j = i + 1, k = number.length - 1; j < k; j++, k--) {
            temp = number[j];
            number[j] = number[k];
            number[k] = temp;
        }

        return number;
    }
}
