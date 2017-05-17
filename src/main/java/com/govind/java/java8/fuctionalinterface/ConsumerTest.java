package com.govind.java.java8.fuctionalinterface;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by 609600403 on 05/10/2016.
 */
public class ConsumerTest {
    public static void main(String[] args) {
        Consumer consumer = (i) -> System.out.println(i);
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        consumer.accept(list);
        for (int i  : list){
            consumer.accept(i);
        }
        System.out.println("Available process "+Runtime.getRuntime().availableProcessors());
    }
}
