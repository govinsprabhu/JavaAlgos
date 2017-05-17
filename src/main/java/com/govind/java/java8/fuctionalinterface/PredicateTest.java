package com.govind.java.java8.fuctionalinterface;

import com.govind.util.arraylist.ArrayListUtils;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Created by 609600403 on 07/07/2016.
 */
public class PredicateTest {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayListUtils<>().getArrayList(new int[]{1, 2, 3, 4, 5, 6, 7});
        testPredicate(arrayList);
    }

    private static void testPredicate(ArrayList<Integer> arrayList) {
        test(arrayList, x -> x % 2 == 0);
    }

    private static Predicate<Integer> evenNumber() {
        return x -> x % 2 == 0;
    }

    private static Predicate<Integer> oddNumber() {
        return x -> x % 2 == 1;
    }

    private static void test(ArrayList<Integer> arrayList, Predicate<Integer> predicate) {
        arrayList.stream().filter(evenNumber()).forEach(x -> System.out.print(x + " "));
        System.out.println();
        arrayList.stream().filter(oddNumber()).forEach(x -> System.out.print(x + " "));
    }

}
