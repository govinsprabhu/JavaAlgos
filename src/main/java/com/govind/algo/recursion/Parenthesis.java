package com.govind.algo.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by govindp on 9/14/2015.
 */
public class Parenthesis {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        parenthesis(arrayList, 3,3,0,new char[7]);
        System.out.println(arrayList);
    }

    private static void parenthesis(ArrayList<String> arrayList, int left, int right, int count, char[] chars){
        if( left == 0 && right == 0){
            arrayList.add(String.valueOf(chars));
        }
        if(left > right || left < 0){
            return ;
        }

        if( left > 0 ){
            chars[count] = '(';
            parenthesis(arrayList, left -1, right, count + 1,chars);
        }
        if(right > left){
            chars[count] = ')';
            parenthesis(arrayList, left, right -1, count + 1, chars);
        }


    }
}
