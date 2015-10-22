package com.govind.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by govindp on 9/25/2015.
 */
public class ArrayListUtils<T> {

    private  T t;
    public static void printArray(List list){
        printArray(list,list.size());
    }

    public static void printArray(List list,int index){
        for (int i = 0; i < index; i++){
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

    public  ArrayList<T> getArrayList(T[] array){
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i =0; i < array.length; i++){
            arrayList.add(array[i]);
        }

        return arrayList;
    }
}
