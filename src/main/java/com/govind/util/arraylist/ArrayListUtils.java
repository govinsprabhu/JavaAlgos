package com.govind.util.arraylist;

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

    public static void printArray(List list, int index){
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




    public  static ArrayList<Integer> getArrayList(int[] array){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i =0; i < array.length; i++){
            arrayList.add(array[i]);
        }

        return arrayList;
    }

    public  static ArrayList<String> getArrayList(String[] array){
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i =0; i < array.length; i++){
            arrayList.add(array[i]);
        }

        return arrayList;
    }



    public static ArrayList<Integer> getIntArrayList(int[] array){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i =0; i < array.length; i++){
            arrayList.add(array[i]);
        }

        return arrayList;
    }


    public static ArrayList<ArrayList<Integer>> getIntArrayList(int[][] array){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i =0; i < array.length; i++){
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int j = 0; j < array[0].length; j++){
                arrayList.add(array[i][j]);
            }
            res.add(arrayList);
        }

        return res;
    }
}
