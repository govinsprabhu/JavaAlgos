package com.govind.algo.platform.hackerearth;

import java.util.ArrayList;
import java.util.List;

class Tes1t {
    public static void main(String[] args) throws Exception {
        // Input can be only Even numbers.
        puzzle(1000);
    }

    public static void puzzle(int totalNumbersCount) {

        List<Integer> numberList = new ArrayList<Integer>();
        for (int i = 0; i < totalNumbersCount; i++) {
            numberList.add(i + 1);
        }


        while (true) {
            numberList = copyNumbers(numberList);
            if (numberList.size() == 1) {
                System.out.println("Number Remaining is " + numberList.get(0));
                break;
            }
        }
    }

    private static List<Integer> copyNumbers(List<Integer> numberList) {

        List<Integer> copiedNumberList = new ArrayList<Integer>();
        for (int i = 1; i < numberList.size(); i = i + 2) {
            copiedNumberList.add(numberList.get(i));
        }
        return copiedNumberList;
    }
}
