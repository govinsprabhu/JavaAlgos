package com.govind.algo.platform.techgig;

import java.util.Arrays;

/**
 * Created by 609600403 on 04/06/2016.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(Solution.cadbury(10, 15, 2, 3));
    }

    public static int ThirstyCrowProblem(int[] input1, int input2, int input3) {
        Arrays.sort(input1);
        if (input1[0] == input1[input1.length - 1]){
            return input3 * input1[0];
        }
        int stones = 0;
        for (int i = 0; i < input3; i++) {
            int min = input1[i];
            if (min == 0){
                continue;
            }
            for (int j = i; j < input1.length; j++) {
                input1[j] -= min;
                stones += min;
            }
        }

        return stones;
    }

    public static int cadbury(int input1, int input2, int input3, int input4) {
        if (input1 <= 0 || input2 <= 0 || input3 <= 0 | input4 <= 0 || input1 > input2 || input3 > input4){
            return 0;
        }
        int totalCount = 0;
        for (int i = input1; i <= input2; i++) {
            for (int j = input3; j <= input4; j++) {
                int cLength = i;
                int cBreadth = j;
                int count = 0;
                while (cLength != 0 && cBreadth != 0) {
                    count++;
                    if (cLength >= cBreadth) {
                        cLength -= cBreadth;

                    } else {
                        cBreadth -= cLength;
                    }
                }
                totalCount += count;
            }
        }

        return totalCount;
    }
}
