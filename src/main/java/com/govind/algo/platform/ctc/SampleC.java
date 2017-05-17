package com.govind.algo.platform.ctc;

import java.util.ArrayList;

/**
 * Created by 609600403 on 21/08/2016.
 */
public class SampleC implements  SampleA, SampleB {
    @Override
    public void sample() {

    }

    public static void main(String[] args) {
        ArrayList<Integer> ar = new ArrayList<>();
        ar.add(1);
        System.out.println(ar.stream().filter(x -> x == 2).findAny().get());

    }
}
