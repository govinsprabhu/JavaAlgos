package com.govind.maven.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by govindp on 9/16/2015.
 */
public class ComparatorExample implements Comparator{
    int h;
    int w;
    int d;

    public ComparatorExample(int h, int w, int d) {
        this.h = h;
        this.w = w;
        this.d = d;
    }

    @Override
    public int compare(Object o1, Object o2) {
        ComparatorExample comparatorExample1 = (ComparatorExample) o1;
        ComparatorExample comparatorExample2 = (ComparatorExample) o2;
        if(comparatorExample1.h >= comparatorExample2.h && comparatorExample1.w >= comparatorExample2.w && comparatorExample1.d >= comparatorExample2.d)
        return 1;
        else {
            return -1;
        }
    }

    public static void main(String[] args) {

        ComparatorExample comparatorExample = new ComparatorExample(3,2,1);
        ComparatorExample comparatorExample1 = new ComparatorExample(6,5,4);
        ComparatorExample comparatorExample2 = new ComparatorExample(9,7,8);
        ComparatorExample comparatorExample3 = new ComparatorExample(12,10,11);
        ArrayList<ComparatorExample> comparatorExamples = new ArrayList<>();
        comparatorExamples.add(comparatorExample);
        comparatorExamples.add(comparatorExample1);
        comparatorExamples.add(comparatorExample2);
        comparatorExamples.add(comparatorExample3);
        //comparatorExamples.add(comparatorExample);


//        Arrays.sort(comparatorExamples,);



    }
}
